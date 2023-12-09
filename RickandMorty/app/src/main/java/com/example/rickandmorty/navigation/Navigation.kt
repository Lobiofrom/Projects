package com.example.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature_characters.presentation.Character
import com.example.feature_characters.presentation.Characters
import com.example.feature_characters.viewmodel.CharacterViewModel
import com.example.feature_locations.presentation.presentation.Locations
import com.example.feature_search.presentation.Search
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(
    navHostController: NavHostController,
    characterViewModel: CharacterViewModel
) {
    NavHost(navController = navHostController, startDestination = "characters") {
        composable(route = "characters") {
            Characters(
                navController = navHostController,
                charactersViewModel = koinViewModel(),
                characterViewModel = characterViewModel
            )
        }
        composable(
            route = "character"
        ) {
            Character(
                characterViewModel = characterViewModel
            )

        }
        composable(route = "locations") {
            Locations(
                navController = navHostController,
                viewModel = koinViewModel(),
                characterViewModel = characterViewModel
            )
        }
        composable(route = "search") {
            Search(
                findCharacterVM = koinViewModel(),
                characterViewModel = characterViewModel,
                navController = navHostController
            )
        }
    }
}
