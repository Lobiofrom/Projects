package com.example.coctails.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coctails.App
import com.example.coctails.data.RecipeDao
import com.example.coctails.entity.Ingredient
import com.example.coctails.entity.Recipe
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyViewModel(
    private val recipeDao: RecipeDao
) : ViewModel() {

    val allRecipes = this.recipeDao.getAllRecipes()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    val allIngredients = this.recipeDao.getAllIngredients()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addRecipe(
        title: String,
        description: String?,
        recipe: String?,
        ingredients: List<Ingredient>?,
        image: Int?
    ) {
        viewModelScope.launch {
            recipeDao.upsertRecipe(
                Recipe(
                    title = title,
                    description = description,
                    recipe = recipe,
                    ingredients = ingredients,
                    image = image
                )
            )
        }
    }

    fun addIngredient(
        ingredient: Ingredient,
        ingredientName: String?
    ) {
        viewModelScope.launch {
            recipeDao.usertIngredient(
                Ingredient(
                    id = ingredient.id.plus(1),
                    ingredientName = ingredientName
                )
            )
        }
    }
}

class MyViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val recipeDao = (application as App).db.recipeDao()
        return MyViewModel(recipeDao) as T
    }
}