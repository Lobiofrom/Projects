package com.example.rickandmorty.presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.navigation.BottomNaviItem
import com.example.rickandmorty.navigation.Navigation
import com.example.rickandmorty.ui.theme.RickandMortyTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val naviItems = listOf(
        BottomNaviItem(
            name = "Characters",
            route = "characters",
            icon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face
        ),
        BottomNaviItem(
            name = "Locations",
            route = "locations",
            icon = Icons.Filled.LocationOn,
            unselectedIcon = Icons.Outlined.LocationOn
        ),
        BottomNaviItem(
            name = "Search",
            route = "search",
            icon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    tonalElevation = 50.dp,
                    modifier = Modifier
                        .shadow(elevation = 15.dp, shape = RoundedCornerShape(6.dp))
                        .padding(start = 6.dp, end = 6.dp)
                        .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                ) {
                    naviItems.forEachIndexed { index, bottomNaviItem ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(bottomNaviItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            label = {
                                Text(text = bottomNaviItem.name)
                            },
                            alwaysShowLabel = false,
                            icon = {
                                if (index == selectedItemIndex) {
                                    Box(
                                        modifier = Modifier
                                            .width(45.dp)
                                            .height(30.dp)
                                            .background(
                                                Color(0xFF24AAAF),
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                    ) {
                                        Icon(
                                            imageVector = bottomNaviItem.icon,
                                            contentDescription = null,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                                } else {
                                    Icon(
                                        imageVector = bottomNaviItem.unselectedIcon,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                }
            }
        ) {
            Navigation(
                navHostController = navController
            )
        }
    }
}