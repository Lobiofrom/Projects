package com.example.kinopoisk.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.kinopoisk.entity.MovieCollection
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCollectionDao {
    @Upsert
    suspend fun addCollection(collection: MovieCollection)

    @Delete
    suspend fun deleteCollection(collection: MovieCollection)

    @Query("select * from MovieCollection")
    fun allCollections(): Flow<List<MovieCollection>>
}
