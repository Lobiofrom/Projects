package com.example.kinopoisk.ui.search

import android.os.IBinder
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.domain.MovieListUseCase
import com.example.kinopoisk.entity.Movie

class SearchPagingSource(
    private val movieListUseCase: MovieListUseCase = MovieListUseCase(),
    private val keyword: String,
    private val yearFrom: Int,
    private val yearTo: Int,
    private val ratingFrom: Int,
    private val ratingTo: Int,
    private val type: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            movieListUseCase.executeSearch(
                type,
                yearFrom,
                yearTo,
                ratingFrom,
                ratingTo,
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