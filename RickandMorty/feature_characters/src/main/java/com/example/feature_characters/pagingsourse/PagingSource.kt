package com.example.feature_characters.pagingsourse

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.feature_characters.domain.model.SimpleCharacter
import com.example.feature_characters.domain.usecases.GetCharactersUseCase

class PagingSource(
    private val useCase: GetCharactersUseCase
) : PagingSource<Int, SimpleCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, SimpleCharacter>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimpleCharacter> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            useCase.execute(page)
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