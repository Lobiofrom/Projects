package com.example.rickandmorty.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNaviItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val unselectedIcon: ImageVector
)
