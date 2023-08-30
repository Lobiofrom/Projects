package com.example.kinopoisk.ui.gallary_fragments

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.domain.MovieListUseCase
import com.example.kinopoisk.entity.Item

class PicturesPagingSource(
    private val id: Int?,
    private val type: String
) : PagingSource<Int, Item>() {
    private val movieListUseCase = MovieListUseCase()

    override fun getRefreshKey(state: PagingState<Int, Item>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: 1
        return kotlin.runCatching {
            movieListUseCase.executePictures(id!!, page, type)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it,
                    prevKey = null,
                    nextKey = if (it.isEmpty()) null else page + 1
                )
            },
            onFailure = { LoadResult.Error(it)}
        )
    }
}