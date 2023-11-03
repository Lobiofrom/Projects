package com.example.kinopoisk.ui.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.App
import com.example.kinopoisk.data.MovieCollectionDao
import com.example.kinopoisk.domain.MovieListUseCase
import com.example.kinopoisk.entity.Movie
import com.example.kinopoisk.entity.dBCollection.MovieId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val dao: MovieCollectionDao,
    private val useCase: MovieListUseCase
) : ViewModel() {

    val collectionList = this.dao.getCollectionsWithMovies()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    private var _viewedList = MutableStateFlow<List<Movie>>(emptyList())
    val viewedList = _viewedList.asStateFlow()

    private var _wantToWatch = MutableStateFlow<List<Movie>>(emptyList())
    val wantToWatch = _wantToWatch.asStateFlow()

    fun getViewedList(list: List<MovieId>) {
        viewModelScope.launch {
            val viewedList = mutableListOf<Movie>()
            for (id in list) {
                val movie = useCase.executeMovieDescription(id.movieId)
                viewedList.add(movie)
            }
            _viewedList.value = viewedList
        }
    }
    fun getWantToWatchList(list: List<MovieId>) {
        viewModelScope.launch {
            val wantToWatch = mutableListOf<Movie>()
            for (id in list) {
                val movie = useCase.executeMovieDescription(id.movieId)
                wantToWatch.add(movie)
            }
            _wantToWatch.value = wantToWatch
        }
    }
}

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = (application as App).db.collectionDao()
        val useCase = MovieListUseCase()
        return ProfileViewModel(dao, useCase) as T
    }
}