package com.example.leads.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.data.bottomNaviItem.BottomNaviItem
import com.example.leads.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNaviBar(
                items = listOf(
                    BottomNaviItem(
                        name = "Leads",
                        route = "leads",
                        icon = R.drawable.leads
                    ),
                    BottomNaviItem(
                        name = "Home",
                        route = "home",
                        icon = R.drawable.home
                    ),
                    BottomNaviItem(
                        name = "Calls",
                        route = "calls",
                        icon = R.drawable.calls
                    ),
                    BottomNaviItem(
                        name = "Chat",
                        route = "chat",
                        icon = R.drawable.chats
                    ),
                    BottomNaviItem(
                        name = "More",
                        route = "more",
                        icon = R.drawable.more
                    )
                ),
                navController = navController,
                onItemClick = { navController.navigate(it.route) }
            )
        }
    ) {
        Navigation(
            navHostController = navController
        )
    }
}