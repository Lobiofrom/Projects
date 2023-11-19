package com.example.domain.repository

import com.example.domain.models.Booking
import com.example.domain.models.Hotel
import com.example.domain.models.Rooms

interface Repository {
    suspend fun getHotel(): Hotel

    suspend fun getRooms(): Rooms

    suspend fun getBooking(): Booking
}