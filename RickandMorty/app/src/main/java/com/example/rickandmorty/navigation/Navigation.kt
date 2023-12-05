package com.example.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature_characters.presentation.Characters
import com.example.feature_characters.viewmodel.CharactersViewModel
import com.example.feature_locations.presentation.Locations
import com.example.feature_search.presentation.Search

@Composable
fun Navigation(
    navHostController: NavHostController,
    viewModel: CharactersViewModel
) {
    NavHost(navController = navHostController, startDestination = "characters") {
        composable(route = "characters") {
            Characters(viewModel)
        }
        composable(route = "locations") {
            Locations()
        }
        composable(route = "search") {
            Search()
        }
    }
}