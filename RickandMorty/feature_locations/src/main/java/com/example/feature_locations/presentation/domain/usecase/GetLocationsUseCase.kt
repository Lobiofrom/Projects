package com.example.feature_locations.presentation.domain.usecase

import com.example.feature_locations.presentation.domain.model.Location
import com.example.feature_locations.presentation.domain.repository.Repository

class GetLocationsUseCase(
    private val repository: Repository
) {
    suspend fun execute(page: Int): List<Location> {
        return repository.getLocations(page)
    }
}