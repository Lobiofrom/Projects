package com.example.kinopoisk.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.data.MovieListRepository
import com.example.kinopoisk.entity.Movie
import com.example.kinopoisk.ui.fullmovielist.PopularPagingSourse
import kotlinx.coroutines.flow.Flow

class SearchViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    //var searchResult: Flow<PagingData<Movie>>? = null

    fun getSearch(keyword: String): Flow<PagingData<Movie>> {
        val search: Flow<PagingData<Movie>> = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { SearchPagingSource(keyword = keyword) }
        ).flow.cachedIn(viewModelScope)
        //searchResult = search
        return search
    }
}