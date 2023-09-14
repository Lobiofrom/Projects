package com.example.coctails.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coctails.entity.Ingredient
import com.example.coctails.entity.Recipe

@Database(
    entities = [Recipe::class, Ingredient::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}