package com.example.feature_search.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.feature_search.domain.findLocationUseCase.FindLocationUseCase
import com.example.feature_search.domain.models.FoundLocation

class PagingSourceLocation(
    private val useCase: FindLocationUseCase,
    private val name: String
) : PagingSource<Int, FoundLocation>() {
    override fun getRefreshKey(state: PagingState<Int, FoundLocation>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FoundLocation> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            useCase.execute(
                page = page,
                name = name
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