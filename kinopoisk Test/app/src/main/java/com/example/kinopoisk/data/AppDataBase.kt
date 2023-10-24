package com.example.kinopoisk.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kinopoisk.entity.dBCollection.Collection
import com.example.kinopoisk.entity.dBCollection.CollectionMovieCrossRef
import com.example.kinopoisk.entity.dBCollection.CollectionWithMovies
import com.example.kinopoisk.entity.dBCollection.MovieCollection
import com.example.kinopoisk.entity.dBCollection.MovieId
import com.example.kinopoisk.utils.MovieIdListConverter

@Database(
    entities = [
        MovieCollection::class,
        Collection::class,
        MovieId::class,
        CollectionMovieCrossRef::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(MovieIdListConverter::class)

abstract class AppDataBase : RoomDatabase() {
    abstract fun collectionDao(): MovieCollectionDao
}