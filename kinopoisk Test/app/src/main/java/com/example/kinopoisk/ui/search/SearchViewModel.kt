package com.example.kinopoisk.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.entity.Movie
import com.example.kinopoisk.entity.StaffItem
import kotlinx.coroutines.flow.Flow

class SearchViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun getSearch(keyword: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { SearchPagingSource(keyword = keyword) }
        ).flow.cachedIn(viewModelScope)
    }

    fun getPersons(name: String): Flow<PagingData<StaffItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { SearchPersonPagingSource(name = name) }
        ).flow.cachedIn(viewModelScope)
    }
}