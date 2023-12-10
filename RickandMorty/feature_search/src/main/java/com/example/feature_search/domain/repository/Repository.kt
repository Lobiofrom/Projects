package com.example.feature_search.domain.repository

import com.apollographql.apollo3.api.Optional
import com.example.feature_search.domain.models.FoundCharacter
import com.example.feature_search.domain.models.FoundLocation

interface Repository {
    suspend fun findCharacter(page: Int, name: String, status: String): List<FoundCharacter>
    suspend fun findLocation(page: Int, name: String): List<FoundLocation>
}