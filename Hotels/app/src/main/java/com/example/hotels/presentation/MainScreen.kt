package com.example.hotels.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.hotels.navigation.Navigation
import com.example.hotels.viewModels.BookingViewModel
import com.example.hotels.viewModels.HotelViewModel
import com.example.hotels.viewModels.RoomsViewModel

@Composable
fun MainScreen(
    hotelViewModel: HotelViewModel,
    roomsViewModel: RoomsViewModel,
    bookingViewModel: BookingViewModel
) {
    val navController = rememberNavController()
    Navigation(
        navHostController = navController,
        hotelViewModel = hotelViewModel,
        roomsViewModel = roomsViewModel,
        bookingViewModel = bookingViewModel
    )
}
