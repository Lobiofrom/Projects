package com.example.domain.useCases

import com.example.domain.models.Booking
import com.example.domain.repository.Repository
import javax.inject.Inject

class GetBookingUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(): Booking {
        return repository.getBooking()
    }
}