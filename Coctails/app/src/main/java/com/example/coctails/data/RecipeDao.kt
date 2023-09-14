package com.example.coctails.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.coctails.entity.Ingredient
import com.example.coctails.entity.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Upsert
    suspend fun upsertRecipe(recipe: Recipe)

    @Upsert
    suspend fun usertIngredient(ingredient: Ingredient)

    @Delete
    suspend fun deleteOneIngredient(ingredient: Ingredient)

    @Query("select * from Recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("select * from Ingredient")
    fun getAllIngredients(): Flow<List<Ingredient>>
}