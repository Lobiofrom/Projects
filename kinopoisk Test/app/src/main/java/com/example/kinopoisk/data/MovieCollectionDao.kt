package com.example.kinopoisk.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.kinopoisk.entity.dBCollection.Collection
import com.example.kinopoisk.entity.dBCollection.CollectionWithMovies
import com.example.kinopoisk.entity.dBCollection.MovieCollection
import com.example.kinopoisk.entity.dBCollection.MovieId
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCollectionDao {

    @Transaction
    @Query("SELECT * FROM Collection")
    fun getCollectionsWithMovies(): Flow<List<CollectionWithMovies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCollection(collection: Collection)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieId(movieId: MovieId)

//    @Transaction
//    suspend fun addCollectionWithMovies(collectionWithMovies: CollectionWithMovies) {
//        addCollection(collectionWithMovies.collection)
//        for (movieId in collectionWithMovies.movies) {
//            addMovieId(movieId)
//        }
//    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCollectionWithMovies(collection: CollectionWithMovies)

//    @Delete
//    suspend fun deleteCollectionWithMovies(collection: CollectionWithMovies)

    @Upsert
    suspend fun addCollection(collection: MovieCollection)

    @Delete
    suspend fun deleteCollection(collection: MovieCollection)

    @Query("select * from MovieCollection")
    fun allCollections(): Flow<List<MovieCollection>>
}
