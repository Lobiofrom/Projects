package com.example.testhotels.data

import com.example.testhotels.entity.passenger.Passenger

data class BookingState(
    val isLoading: Boolean = true,
    val passengerList: List<Passenger> = listOf(Passenger())
)
