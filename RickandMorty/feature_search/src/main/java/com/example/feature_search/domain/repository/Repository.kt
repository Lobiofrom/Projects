package com.example.feature_search.domain.repository

import com.apollographql.apollo3.api.Optional
import com.example.feature_search.domain.models.FoundCharacter

interface Repository {
    suspend fun findCharacter(page: Int, name: String, status: String): List<FoundCharacter>
}