package com.example.kinopoisk.ui.search

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

    val yearFrom = 1950
    val yearTo = 2023
    val ratingFrom = 5
    val ratingTo = 10
    val type = "ALL"

    fun getMovies(keyword: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingSource(
                    keyword = keyword,
                    yearFrom = yearFrom,
                    yearTo = yearTo,
                    ratingFrom = ratingFrom,
                    ratingTo = ratingTo,
                    type = type
                )
            }
        ).flow.cachedIn(viewModelScope)
    }

    fun getPersons(name: String): Flow<PagingData<StaffItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { SearchPersonPagingSource(name = name) }
        ).flow.cachedIn(viewModelScope)
    }
}
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is dashboard Fragment"
//    }
//    val text: LiveData<String> = _text
//
//    private var _persons = MutableStateFlow<List<StaffItem>>(emptyList())
//    private var _movies = MutableStateFlow<List<Movie>>(emptyList())
//
//    val combinedFlow: Flow<List<BaseMediaItem>> =
//        _persons.combine(_movies) { persons, movies ->
//            persons.map { it } + movies.map { it }
//        }
