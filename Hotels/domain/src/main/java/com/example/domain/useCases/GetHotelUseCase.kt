package com.example.domain.useCases

import com.example.domain.models.Hotel
import com.example.domain.repository.Repository
import javax.inject.Inject

class GetHotelUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(): Hotel {
        return repository.getHotel()
    }
}