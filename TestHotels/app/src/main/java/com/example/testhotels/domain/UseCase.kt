package com.example.testhotels.domain

import com.example.testhotels.data.BookingDto
import com.example.testhotels.data.HotelDto
import com.example.testhotels.data.Repository
import com.example.testhotels.entity.room.Room

class UseCase {

    private val repository = Repository()

    suspend fun executeHotel(): HotelDto {
        return repository.getHotel()
    }

    suspend fun executeRooms(): List<Room> {
        return repository.getRooms()
    }

    suspend fun executeBooking(): BookingDto {
        return repository.getBooking()
    }
}