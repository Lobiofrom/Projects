package com.example.kinopoisk.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.domain.MovieListUseCase
import com.example.kinopoisk.entity.StaffItem

class SearchPersonPagingSource(
    private val useCase: MovieListUseCase = MovieListUseCase(),
    private val name: String
) : PagingSource<Int, StaffItem>() {
    override fun getRefreshKey(state: PagingState<Int, StaffItem>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StaffItem> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            useCase.executePersonSearch(name, page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it) }
        )
    }
}