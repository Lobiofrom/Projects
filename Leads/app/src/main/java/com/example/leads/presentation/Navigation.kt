package com.example.leads.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(
    navHostController: NavHostController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(navController = navHostController, startDestination = "leads") {
            composable(route = "leads") {
                Leads()
            }
            composable(route = "home") {
                Home()
            }
            composable(route = "calls") {
                Calls()
            }
            composable(route = "chat") {
                Chat()
            }
            composable(route = "more") {
                More()
            }
        }
    }
}