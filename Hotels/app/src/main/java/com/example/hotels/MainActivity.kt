package com.example.hotels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.hotels.presentation.MainScreen
import com.example.hotels.ui.theme.HotelsTheme
import com.example.hotels.viewModels.BookingViewModel
import com.example.hotels.viewModels.HotelViewModel
import com.example.hotels.viewModels.RoomsViewModel

class MainActivity : ComponentActivity() {

    private val hotelComponent = App().appComponent1
    private val roomsComponent = App().appComponent2
    private val bookingComponent = App().appComponent3

    private val hotelViewModel: HotelViewModel by viewModels {
        hotelComponent
    }
    private val roomsViewModel: RoomsViewModel by viewModels {
        roomsComponent
    }
    private val bookingViewModel: BookingViewModel by viewModels {
        bookingComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotelsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        hotelViewModel = hotelViewModel,
                        roomsViewModel = roomsViewModel,
                        bookingViewModel
                    )
                }
            }
        }
    }
}