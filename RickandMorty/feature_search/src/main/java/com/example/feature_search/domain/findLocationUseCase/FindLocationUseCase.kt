package com.example.feature_search.domain.findLocationUseCase

import com.example.feature_search.domain.models.FoundLocation
import com.example.feature_search.domain.repository.Repository

class FindLocationUseCase(
    private val repository: Repository
) {
    suspend fun execute(page: Int, name: String): List<FoundLocation> {
        return repository.findLocation(page, name)
    }
}