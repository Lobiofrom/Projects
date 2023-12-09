package com.example.feature_search.domain.findCharacterUseCase

import com.example.feature_search.domain.models.FoundCharacter
import com.example.feature_search.domain.repository.Repository

class FindCharacterUseCase(
    private val repository: Repository
) {
    suspend fun execute(page: Int, name: String, status: String): List<FoundCharacter> {
        return repository.findCharacter(page = page, name = name, status = status)
    }
}