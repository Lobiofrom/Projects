package com.example.logistics

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.logistics.presentation.onboarding.AddPhoneNumber
import com.example.logistics.presentation.onboarding.EnterSms
import com.example.logistics.presentation.onboarding.Greeting
import com.example.logistics.presentation.main.MainScreen
import com.example.logistics.presentation.viewmodel.MyViewModel
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(viewModel: MyViewModel) {
    var showAddPhoneNumber by remember {
        mutableStateOf(false)
    }

    var showEnterSms by remember {
        mutableStateOf(false)
    }

    var showBottomNavi by remember {
        mutableStateOf(false)
    }

    var phoneNumber by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        delay(500)
        showAddPhoneNumber = true
    }

    if (!showAddPhoneNumber) {
        Greeting()
    } else {
        AddPhoneNumber(
            onContinueClick = {
                showAddPhoneNumber = false
                showEnterSms = true
                phoneNumber = it
            }
        )
    }
    if (showEnterSms) EnterSms(
        phoneNumber = phoneNumber,
        onClick = {
            showAddPhoneNumber = true
            showEnterSms = false
        },
        onButtonClick = {
            showBottomNavi = true
            showEnterSms = false
        }
    )
    if (showBottomNavi) {
        MainScreen(
            viewModel
        )
    }
}