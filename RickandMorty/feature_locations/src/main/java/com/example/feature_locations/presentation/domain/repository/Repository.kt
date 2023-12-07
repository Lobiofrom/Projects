package com.example.feature_locations.presentation.domain.repository

import com.example.feature_locations.presentation.domain.model.Location

interface Repository {
    suspend fun getLocations(page: Int): List<Location>
}