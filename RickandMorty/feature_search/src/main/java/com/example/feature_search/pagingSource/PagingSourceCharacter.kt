package com.example.feature_search.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.feature_search.domain.findCharacterUseCase.FindCharacterUseCase
import com.example.feature_search.domain.models.FoundCharacter

class PagingSourceCharacter(
    private val useCase: FindCharacterUseCase,
    private val name: String,
    private val status: String,

    ) : PagingSource<Int, FoundCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, FoundCharacter>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FoundCharacter> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            useCase.execute(
                page = page,
                name = name,
                status = status
            )
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }
}