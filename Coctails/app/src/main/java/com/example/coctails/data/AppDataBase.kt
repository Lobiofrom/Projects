package com.example.coctails.data

import com.example.coctails.entity.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.coctails.entity.Ingredient
import com.example.coctails.entity.Recipe

@Database(
    entities = [Recipe::class, Ingredient::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)

abstract class AppDataBase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}