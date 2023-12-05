package com.example.feature_characters.domain.usecases

import com.example.feature_characters.domain.model.SimpleCharacter
import com.example.feature_characters.domain.repository.Repository

class GetCharactersUseCase(
    private val repository: Repository
) {
    suspend fun execute(page: Int): List<SimpleCharacter> {
        return repository.getCharacters(page)
    }
}