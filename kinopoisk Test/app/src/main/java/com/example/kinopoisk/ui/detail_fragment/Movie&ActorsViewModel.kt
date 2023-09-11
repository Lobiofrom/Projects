package com.example.kinopoisk.ui.detail_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.data.State
import com.example.kinopoisk.domain.MovieListUseCase
import com.example.kinopoisk.entity.Movie
import com.example.kinopoisk.entity.Season
import com.example.kinopoisk.entity.StaffItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MovieActorsSimilarsViewModel : ViewModel() {

    private val useCase = MovieListUseCase()

    private val _movieDescription = MutableStateFlow<Movie?>(null)
    val movieDescription = _movieDescription.asStateFlow()

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val _staffList = MutableStateFlow<ArrayList<StaffItem>>(ArrayList())
    val staffList = _staffList.asStateFlow()

    private val _similars = MutableStateFlow<List<Movie>>(emptyList())
    val similars = _similars.asStateFlow()

    private val _series = MutableStateFlow<List<Season>>(emptyList())
    val series = _series.asStateFlow()

    fun getAllDetails(movieId: Int) {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val similars = useCase.executeSimilars(movieId)
                _similars.value = similars
                val description = useCase.executeMovieDescription(movieId)
                _movieDescription.value = description
                val staffList = useCase.executeStaff(movieId)
                _staffList.value = staffList
                val series = useCase.executeSereies(movieId)
                _series.value = series
                _state.value = State.Success
            } catch (e: Exception) {
                _state.value = State.Error
            }
        }
    }
}