package com.example.logistics.presentation.main

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.logistics.data.BottomNavItem
import com.example.logistics.presentation.navigation.BottomNaviBar
import com.example.logistics.presentation.navigation.Navigation
import com.example.logistics.presentation.viewmodel.MyViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MyViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNaviBar(
                items = listOf(
                    BottomNavItem(
                        name = "Task",
                        route = "tasks",
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Schedule",
                        route = "schedule",
                        icon = Icons.Default.DateRange
                    ),
                    BottomNavItem(
                        name = "Chat",
                        route = "chat",
                        icon = Icons.Default.Notifications
                    ),
                    BottomNavItem(
                        name = "Profile",
                        route = "profile",
                        icon = Icons.Default.Face
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Navigation(navController = navController, viewModel = viewModel)
    }
}

//@Preview
//@Composable
//fun MainPreview() {
//    LogisticsTheme {
//        MainScreen()
//    }
//}