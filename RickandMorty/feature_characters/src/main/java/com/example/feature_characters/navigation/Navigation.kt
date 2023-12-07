package com.example.feature_characters.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature_characters.presentation.Character
import com.example.feature_characters.presentation.Characters
import com.example.feature_characters.viewmodel.CharactersViewModel

@Composable
fun CharactersNavigation(
//    viewModel: CharactersViewModel,
//    state: CharactersViewModel.CharacterState
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "characters") {
        composable(route = "characters") {
            Characters(navController = navController)
//            Log.d(
//                "Characters", "Characters===== ${
//                    Characters(navController = navController)
//
//                }"
//            )
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
    }
}