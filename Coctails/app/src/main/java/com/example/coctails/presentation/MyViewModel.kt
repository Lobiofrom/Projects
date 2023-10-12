package com.example.coctails.presentation

import android.app.Application
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.coctails.App
import com.example.coctails.data.RecipeDao
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

    fun addRecipe(
        id: Int,
        title: String,
        description: String?,
        recipe: String?,
        ingredients: SnapshotStateList<String>?,
        image: String
    ) {
        viewModelScope.launch {
            recipeDao.upsertRecipe(
                Recipe(
                    id = id,
                    title = title,
                    description = description,
                    recipe = recipe,
                    ingredients = ingredients,
                    image = image
                )
            )
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            recipeDao.deleteRecipe(recipe)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MyViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val recipeDao = (application as App).db.recipeDao()
        return MyViewModel(recipeDao) as T
    }
}