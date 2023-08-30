package com.example.myapplication.presentation

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.DetailInfoDto
import com.example.myapplication.domain.GetInfoUseCase
import com.example.myapplication.domain.GetPlacesUseCase
import com.example.myapplication.entity.Feature
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
@Composable
fun MapScreen() {

    val coroutineScope = rememberCoroutineScope()
    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                mapToolbarEnabled = true,
                zoomControlsEnabled = false
            )
        )
    }
    val properties by remember {
        mutableStateOf(
            MapProperties(
                mapType = MapType.NORMAL,
                isMyLocationEnabled = true
            )
        )
    }
    val context = LocalContext.current
    val fusedLocationProviderClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val locationResult = fusedLocationProviderClient.lastLocation
    var deviceLocation by remember {
        mutableStateOf<LatLng?>(null)
    }
    var lastKnownLocation by remember {
        mutableStateOf<Location?>(null)
    }
    var places by remember {
        mutableStateOf(emptyList<Feature>())
    }
    var info by remember {
        mutableStateOf<DetailInfoDto?>(null)
    }
    var showText by remember { mutableStateOf(false) }

    locationResult.addOnSuccessListener {
        if (it != null) {
            deviceLocation = LatLng(it.latitude, it.longitude)
            lastKnownLocation = it
        }
    }

    if (deviceLocation != null && lastKnownLocation != null) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(deviceLocation!!, 15f)
        }
        LaunchedEffect(Unit) {
            val getPlacesUseCase = GetPlacesUseCase(lastKnownLocation!!.longitude, lastKnownLocation!!.latitude)
            places = getPlacesUseCase.execute()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GoogleMap(
                properties = properties,
                uiSettings = uiSettings,
                cameraPositionState = cameraPositionState
            ) {
                for (place in places) {

                    val position = LatLng(place.geometry.coordinates[1], place.geometry.coordinates[0])

                    MarkerInfoWindowContent(
                        state = MarkerState(position = position),
                        onInfoWindowClick = {
                            val getInfoUseCase = GetInfoUseCase(place.properties.xid)
                            coroutineScope.launch {
                                info = getInfoUseCase.execute()
                                showText = true
                            }
                        },
                        onInfoWindowClose = {
                            showText = false
                        }
                    ) {
                        Column {
                            Text(it.title ?: place.properties.name, color = Color.Red)
                        }
                    }
                }
            }

            val km = lastKnownLocation!!.speed.times(3.6).toInt()

            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd).padding(16.dp),
                text = "$km km/h",
                fontSize = 15.sp,
            )
            if (info != null && showText) {
                val wikipediaText = info?.wikipedia_extracts?.text ?: "Нет информации"
                val interactionSource = remember { MutableInteractionSource() }
                var isExpanded by remember {
                    mutableStateOf(false)
                }
                val surfaceColor by animateColorAsState(
                    if (isExpanded)
                        MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface, label = ""
                )

                Surface(
                    modifier = Modifier
                        .animateContentSize().padding(1.dp)
                        .align(Alignment.Center)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = rememberRipple(
                                bounded = true,
                                //radius = 250.dp,
                                color = Color.DarkGray
                            )
                        ) { isExpanded = !isExpanded },
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 5.dp,
                    color = surfaceColor,
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(16.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        text = wikipediaText,
                        fontSize = 15.sp,
                        color = if (isExpanded) Color.White else Color.Black,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}