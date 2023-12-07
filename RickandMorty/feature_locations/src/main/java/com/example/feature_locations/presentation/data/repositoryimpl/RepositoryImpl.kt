package com.example.feature_locations.presentation.data.repositoryimpl

import com.apollographql.apollo3.ApolloClient
import com.example.api.LocationsQuery
import com.example.feature_locations.presentation.data.mapper.toLocation
import com.example.feature_locations.presentation.domain.model.Location
import com.example.feature_locations.presentation.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl(
    private val apolloClient: ApolloClient
) : Repository {
    override suspend fun getLocations(page: Int): List<Location> {
        return withContext(Dispatchers.IO) {
            apolloClient
                .query(LocationsQuery(page))
                .execute()
                .data
                ?.locations?.results
                ?.mapNotNull {
                    it?.toLocation()
                }
                ?: emptyList()
        }
    }
}