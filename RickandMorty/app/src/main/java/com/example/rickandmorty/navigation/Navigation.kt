package com.example.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature_characters.presentation.Character
import com.example.feature_characters.presentation.Characters
import com.example.feature_characters.viewmodel.CharacterViewModel
import com.example.feature_characters.viewmodel.CharactersViewModel
import com.example.feature_locations.presentation.presentation.Locations
import com.example.feature_locations.presentation.viewmodel.LocationsViewModel
import com.example.feature_search.presentation.Search

@Composable
fun Navigation(
    navHostController: NavHostController,
    charactersViewModel: CharactersViewModel,
    characterViewModel: CharacterViewModel,
    state: CharacterViewModel.CharacterState,
    locationsViewModel: LocationsViewModel
) {
    NavHost(navController = navHostController, startDestination = "characters") {
        composable(route = "characters") {
            Characters(
                navController = navHostController,
                charactersViewModel = charactersViewModel,
                characterViewModel = characterViewModel
            )
        }
        composable(
            route = "character"
        ) {
            Character(
                state = state
            )
        }
        composable(route = "locations") {
            Locations(locationsViewModel, navHostController, characterViewModel)
        }
        composable(route = "search") {
            Search()
        }
    }
}
