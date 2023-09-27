package com.example.kinopoisk.ui.search

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.domain.MovieListUseCase
import com.example.kinopoisk.entity.BaseMediaItem
import com.example.kinopoisk.entity.Movie
import com.example.kinopoisk.entity.StaffItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.onEach

class SearchViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val useCase = MovieListUseCase()

    var _persons = MutableStateFlow<List<StaffItem>>(emptyList())
    var _movies = MutableStateFlow<List<Movie>>(emptyList())

    val persons = _persons.asStateFlow()
    val movies = _movies.asStateFlow()


    val combinedFlow: Flow<List<BaseMediaItem>> =
        _persons.combine(_movies) { persons, movies ->
            persons.map { it } + movies.map { it }
        }.also {
            it.onEach { data ->
                Log.d("SearchViewModel", "Combined Flow Emitting: $data")
            }
        }

    fun search(keyword: String) {
        viewModelScope.launch {
            val list = useCase.executeSearch(
                "ALL",
                1970,
                2023,
                5,
                10,
                keyword = keyword,
                1
            )
            _movies.value = list
        }
    }

    fun searchP(keyword: String) {
        viewModelScope.launch {
            val list = useCase.executePersonSearch(
                keyword,
                1
            )
            _persons.value = list
        }
    }


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