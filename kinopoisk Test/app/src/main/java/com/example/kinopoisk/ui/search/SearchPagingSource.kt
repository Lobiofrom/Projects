package com.example.kinopoisk.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.domain.MovieListUseCase
import com.example.kinopoisk.entity.Movie

class SearchPagingSource(
    private val movieListUseCase: MovieListUseCase = MovieListUseCase(),
    private val keyword: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            movieListUseCase.executeSearch(
                "ALL",
                1970,
                2023,
                5,
                10,
                keyword = keyword,
                page
            )
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