package com.example.coctails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coctails.ui.theme.CoctailsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoctailsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var showGreeting by remember {
        mutableStateOf(true)
    }
    if (showGreeting) {
        Greeting(
            name = "My cocktails",
            onContinueClicked = { showGreeting = false }
        )
    } else {
        AddCocktail(
            onIconClicked = { showGreeting = true }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoctailsTheme {
        MyApp()
    }
}