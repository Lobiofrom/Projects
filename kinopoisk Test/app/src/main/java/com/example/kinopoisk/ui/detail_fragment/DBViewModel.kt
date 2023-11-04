package com.example.kinopoisk.ui.detail_fragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.App
import com.example.kinopoisk.data.MovieCollectionDao
import com.example.kinopoisk.entity.dBCollection.Collection
import com.example.kinopoisk.entity.dBCollection.CollectionWithMovies
import com.example.kinopoisk.entity.dBCollection.MovieCollection
import com.example.kinopoisk.entity.dBCollection.MovieId
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DBViewModel(
    private val dao: MovieCollectionDao
) : ViewModel() {

    val allCollectionsWithMovies = this.dao.getCollectionsWithMovies().asLiveData()

    fun addCollection(title: String) {
        viewModelScope.launch {
            dao.insertCollection(
                Collection(
                    collectionName = title
                )
            )
        }
    }

    fun addMovieId(id: Int, collectionId: Long) {
        viewModelScope.launch {
            dao.insertMovie(
                MovieId(
                    collectionId = collectionId,
                    movieId = id
                )
            )
        }
    }

    fun deleteMovieId(id: MovieId) {
        viewModelScope.launch {
            dao.deleteMovieId(id)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DBViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = (application as App).db.collectionDao()
        return DBViewModel(dao) as T
    }
}