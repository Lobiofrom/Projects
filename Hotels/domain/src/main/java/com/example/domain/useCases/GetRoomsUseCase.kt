package com.example.domain.useCases

import com.example.domain.models.Room
import com.example.domain.repository.Repository
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun execute(): List<Room> {
        return repository.getRooms().rooms
    }
}