package com.example.testhotels.domain

import com.example.testhotels.data.HotelDto
import com.example.testhotels.data.Repository

class UseCase {

    private val repository = Repository()

    suspend fun executeHotel(): HotelDto {
        return repository.getHotel()
    }
}