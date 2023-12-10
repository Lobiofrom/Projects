package com.example.rickandmorty.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.feature_characters.presentation.Character
import com.example.feature_characters.presentation.Characters
import com.example.feature_locations.presentation.presentation.Locations
import com.example.feature_search.presentation.Search
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "characters") {
        composable(route = "characters") {
            Characters(
                navController = navHostController,
                charactersViewModel = koinViewModel()
            )
        }
        composable(
            route = "character/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("id")
                ?.let {
                    Character(
                        id = it,
                        characterViewModel = koinViewModel()
                    )

                }
            Log.d("OneCharacterScreen", "OneCharacterScreen=========")
        }
        composable(route = "locations") {
            Locations(
                navController = navHostController,
                viewModel = koinViewModel()
            )
        }
        composable(route = "search") {
            Search(
                findCharacterVM = koinViewModel(),
                navController = navHostController,
                findLocationVM = koinViewModel()
            )
        }
    }
}
