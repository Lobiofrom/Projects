package com.example.kinopoisk.ui.fullmovielist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.domain.MovieListUseCase
import com.example.kinopoisk.entity.Movie

class SelectionsPagingSourse(
    private val countries: Int,
    private val genres: Int,
    private val type: String,
    private val yearFrom: Int,
) : PagingSource<Int, Movie>() {
    private val movieListUseCase = MovieListUseCase()

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            movieListUseCase.executeSelection(countries, genres, type, yearFrom, page)
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