package com.example.feature_locations.presentation.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.feature_locations.presentation.domain.model.Location
import com.example.feature_locations.presentation.domain.usecase.GetLocationsUseCase

class PagingSource(
    private val useCase: GetLocationsUseCase
) : PagingSource<Int, Location>() {
    override fun getRefreshKey(state: PagingState<Int, Location>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
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