package com.example.coctails.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coctails.entity.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Upsert
    suspend fun upsertRecipe(recipe: Recipe)

    @Query("select * from Recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)
}