package com.example.kinopoisk.ui.detail_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.data.MovieDescriptionDto
import com.example.kinopoisk.data.State
import com.example.kinopoisk.domain.MovieListUseCase
import com.example.kinopoisk.entity.Movie
import com.example.kinopoisk.entity.StaffItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MovieActorsSimilarsViewModel : ViewModel() {

    private val useCase = MovieListUseCase()

    private val _movieDescription = MutableStateFlow<MovieDescriptionDto?>(null)
    val movieDescription = _movieDescription.asStateFlow()

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val _staffList = MutableStateFlow<ArrayList<StaffItem>>(ArrayList())
    val staffList = _staffList.asStateFlow()

    private val _similars = MutableStateFlow<List<Movie>>(emptyList())
    val similars = _similars.asStateFlow()

    fun getSimilars(movieId: Int) {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val similars = useCase.executeSimilars(movieId)
                _similars.value = similars
            } catch (e: Exception) {
                _state.value = State.Error
            }
        }
    }

    fun getDescription(id: Int) {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val description = useCase.executeMovieDescription(id)
                _movieDescription.value = description
                _state.value = State.Success
            } catch (e: Exception) {
                _state.value = State.Error
            }
        }
    }

    fun getStaff(filmId: Int) {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val staffList = useCase.executeStaff(filmId)
                _staffList.value = staffList
                _state.value = State.Success
            } catch (e: Exception) {
                _state.value = State.Error
            }
        }
    }
}