package com.example.feature_characters.data

import com.apollographql.apollo3.ApolloClient
import com.example.api.CharacterQuery
import com.example.api.CharactersQuery
import com.example.feature_characters.domain.model.Character
import com.example.feature_characters.domain.model.SimpleCharacter
import com.example.feature_characters.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoImpl(
    private val apollo: ApolloClient
) : Repository {
    override suspend fun getCharacters(page: Int): List<SimpleCharacter> {
        return withContext(Dispatchers.IO) {
            apollo
                .query(CharactersQuery(page))
                .execute()
                .data
                ?.characters?.results
                ?.mapNotNull { it?.toSimpleCharacter() }
                ?: emptyList()
        }
    }

    override suspend fun getCharacter(id: String): Character? {
        return withContext(Dispatchers.IO) {
            apollo
                .query(CharacterQuery(id))
                .execute()
                .data
                ?.character
                ?.toCharacter()
        }
    }
}