package com.example.data.repositoryImpl

import com.example.data.dto.toBooking
import com.example.data.dto.toHotel
import com.example.data.dto.toRooms
import com.example.data.retrofit.RetrofitAndApi
import com.example.domain.models.Booking
import com.example.domain.models.Hotel
import com.example.domain.models.Rooms
import com.example.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val retrofitAndApi: RetrofitAndApi
) : Repository {
    override suspend fun getHotel(): Hotel {
        return retrofitAndApi.api.getHotel().toHotel()
    }

    override suspend fun getRooms(): Rooms {
        return retrofitAndApi.api.getRooms().toRooms()
    }

    override suspend fun getBooking(): Booking {
        return retrofitAndApi.api.getBooking().toBooking()
    }
}