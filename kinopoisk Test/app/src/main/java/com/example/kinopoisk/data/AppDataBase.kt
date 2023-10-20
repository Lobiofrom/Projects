package com.example.kinopoisk.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kinopoisk.entity.MovieCollection
import com.example.kinopoisk.utils.MovieIdListConverter

@Database(
    entities = [MovieCollection::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(MovieIdListConverter::class)

abstract class AppDataBase : RoomDatabase() {
    abstract fun collectionDao(): MovieCollectionDao
}