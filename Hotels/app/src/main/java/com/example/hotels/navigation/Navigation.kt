package com.example.hotels.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hotels.presentation.BookingScreen
import com.example.hotels.presentation.FinalScreen
import com.example.hotels.presentation.HotelScreen
import com.example.hotels.presentation.RoomsScreen
import com.example.hotels.viewModels.BookingViewModel
import com.example.hotels.viewModels.HotelViewModel
import com.example.hotels.viewModels.RoomsViewModel

@Composable
fun Navigation(
    navHostController: NavHostController,
    hotelViewModel: HotelViewModel,
    roomsViewModel: RoomsViewModel,
    bookingViewModel: BookingViewModel
) {
    NavHost(navController = navHostController, startDestination = "hotel") {
        composable(route = "hotel") {
            HotelScreen(
                hotelViewModel, navHostController
            )
        }
        composable(
            route = "rooms/{hotelName}",
            arguments = listOf(navArgument("hotelName") { type = NavType.StringType })
        ) {backStackEntry ->
            val hotelName = backStackEntry.arguments?.getString("hotelName")
            if (hotelName != null) {
                RoomsScreen(hotelName = hotelName, navHostController, roomsViewModel)
            }
        }
        composable(route = "booking") {
            BookingScreen(navHostController, bookingViewModel)
        }
        composable(route = "final") {
            FinalScreen(navHostController)
        }
    }
}