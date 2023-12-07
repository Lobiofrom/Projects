package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.rickandmorty.presentation.MainScreen
import com.example.rickandmorty.ui.theme.RickandMortyTheme

class MainActivity : ComponentActivity() {

//    private val viewModel: CharactersViewModel by viewModels {
//        CharactersViewModelFactory()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickandMortyTheme {
                //val state by viewModel.state.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        //    viewModel, state
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