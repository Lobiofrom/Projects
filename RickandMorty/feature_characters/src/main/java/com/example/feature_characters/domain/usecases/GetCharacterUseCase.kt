package com.example.feature_characters.domain.usecases

import com.example.feature_characters.domain.model.Character
import com.example.feature_characters.domain.repository.Repository

class GetCharacterUseCase(
    private val repository: Repository
) {
    suspend fun execute(id: String): Character? {
        return repository.getCharacter(id)
    }
}