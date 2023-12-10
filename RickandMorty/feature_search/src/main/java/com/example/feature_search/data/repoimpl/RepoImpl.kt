package com.example.feature_search.data.repoimpl

import com.apollographql.apollo3.ApolloClient
import com.example.api.GetCharacterQuery
import com.example.api.GetLocationQuery
import com.example.feature_search.data.mappers.toFoundCharacter
import com.example.feature_search.data.mappers.toFoundLocation
import com.example.feature_search.domain.models.FoundCharacter
import com.example.feature_search.domain.models.FoundLocation
import com.example.feature_search.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoImpl(
    private val apollo: ApolloClient
) : Repository {
    override suspend fun findCharacter(
        page: Int,
        name: String,
        status: String
    ): List<FoundCharacter> {
        return withContext(Dispatchers.IO) {
            apollo
                .query(GetCharacterQuery(page = page, name = name, status = status))
                .execute()
                .data
                ?.characters?.results
                ?.mapNotNull {
                    it?.toFoundCharacter()
                }
                ?: emptyList()
        }
    }

    override suspend fun findLocation(page: Int, name: String): List<FoundLocation> {
        return withContext(Dispatchers.IO) {
            apollo
                .query(GetLocationQuery(page = page, name = name))
                .execute()
                .data
                ?.locations?.results
                ?.mapNotNull {
                    it?.toFoundLocation()
                }
                ?: emptyList()
        }
    }
}