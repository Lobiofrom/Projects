package com.example.kinopoisk.ui.actor_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.data.PersonDto
import com.example.kinopoisk.domain.MovieListUseCase
import com.example.kinopoisk.entity.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ActorViewModel : ViewModel() {
    private val movieListUseCase = MovieListUseCase()

    private var _actorInfo = MutableStateFlow<PersonDto?>(null)
    val actorInfo = _actorInfo.asStateFlow()

    private var _filteredList = MutableStateFlow<List<Movie>>(emptyList())
    val filteredList = _filteredList.asStateFlow()

    fun getActorInfo(id: Int) {
        viewModelScope.launch {
            val person = movieListUseCase.executePerson(id)

            val idList = person.films
                .sortedByDescending {
                    it.rating?.toDouble()
                }
                .take(8)
                .map {
                    it.filmId
                }
                .distinct()
            _actorInfo.value = person

            val list = mutableListOf<Movie>()

            for (filmId in idList) {
                val movie = movieListUseCase.executeMovieDescription(filmId)
                list.add(movie)
            }
            _filteredList.value = list
        }
    }
}