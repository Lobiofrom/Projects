package com.example.testhotels.data

import com.example.testhotels.entity.room.Room

class Repository {
    private val retrofitAndApi = RetrofitAndApi()

    suspend fun getHotel(): HotelDto {
        return retrofitAndApi.api.getHotel()
    }

    suspend fun getRooms(): List<Room> {
        return retrofitAndApi.api.getRooms().rooms
    }

    suspend fun getBooking(): BookingDto {
        return retrofitAndApi.api.getBooking()
    }
}