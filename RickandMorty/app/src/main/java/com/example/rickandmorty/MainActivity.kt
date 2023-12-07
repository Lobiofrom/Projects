package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.feature_characters.viewmodel.CharacterViewModel
import com.example.feature_characters.viewmodel.CharacterViewModelFactory
import com.example.feature_characters.viewmodel.CharactersViewModel
import com.example.feature_characters.viewmodel.CharactersViewModelFactory
import com.example.feature_locations.presentation.viewmodel.LocationsViewModel
import com.example.feature_locations.presentation.viewmodel.LocationsViewModelFactory
import com.example.rickandmorty.presentation.MainScreen
import com.example.rickandmorty.ui.theme.RickandMortyTheme

class MainActivity : ComponentActivity() {

    private val charactersViewModel: CharactersViewModel by viewModels {
        CharactersViewModelFactory()
    }
    private val characterViewModel: CharacterViewModel by viewModels {
        CharacterViewModelFactory()
    }
    private val locationsViewModel: LocationsViewModel by viewModels {
        LocationsViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickandMortyTheme {
                val state by characterViewModel.state.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        charactersViewModel, characterViewModel, state, locationsViewModel
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Preview(
//    name = "Dark",
//    uiMode = Configuration.UI_MODE_NIGHT_YES
//)
//@Composable
//fun GreetingPreview() {
//    RickandMortyTheme {
//        MainScreen()
//    }
//}