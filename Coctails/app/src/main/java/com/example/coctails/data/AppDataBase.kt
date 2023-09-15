package com.example.coctails.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.coctails.entity.Converters
import com.example.coctails.entity.Recipe

@Database(
    entities = [
        Recipe::class
               ],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)

abstract class AppDataBase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}