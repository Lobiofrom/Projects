package com.example.kinopoisk.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.kinopoisk.entity.dBCollection.Collection
import com.example.kinopoisk.entity.dBCollection.CollectionWithMovies
import com.example.kinopoisk.entity.dBCollection.MovieId
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieCollectionDao {

    @Insert
    suspend fun insertCollection(collection: Collection): Long

    @Insert
    suspend fun insertMovie(movieId: MovieId)

    @Transaction
    @Query("SELECT * FROM collections")
    fun getCollectionsWithMovies(): Flow<List<CollectionWithMovies>>

    @Transaction
    @Query("SELECT * FROM collections")
    fun getCollectionsWithMoviesNoFlow(): List<CollectionWithMovies>

    @Delete
    suspend fun deleteCollection(collection: Collection)

    @Delete
    suspend fun deleteMovieId(movieId: MovieId)
}
