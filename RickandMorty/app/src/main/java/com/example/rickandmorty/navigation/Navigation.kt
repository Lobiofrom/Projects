package com.example.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.feature_characters.presentation.Character
import com.example.feature_characters.presentation.Characters
import com.example.feature_locations.presentation.Locations
import com.example.feature_search.presentation.Search

@Composable
fun Navigation(
    navHostController: NavHostController,
//    viewModel: CharactersViewModel,
//    state: CharactersViewModel.CharacterState
) {

    NavHost(navController = navHostController, startDestination = "characters") {
        composable(route = "characters") {
            Characters(
                navController = navHostController
            )
        }
        composable(
            route = "character/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            if (id != null) {
                Character(id)
            }
        }
        //}

        composable(route = "locations") {
            Locations()
        }
        composable(route = "search") {
            Search()
        }
    }
}
