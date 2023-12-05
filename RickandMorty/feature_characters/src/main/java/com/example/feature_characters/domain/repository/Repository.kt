package com.example.feature_characters.domain.repository

import com.example.feature_characters.domain.model.Character
import com.example.feature_characters.domain.model.SimpleCharacter

interface Repository {
    suspend fun getCharacters(page: Int): List<SimpleCharacter>
    suspend fun getCharacter(id: String): Character?
}