package com.example.coctails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

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
            onIconClicked = { showGreeting = true },
            onCancelClick = { showGreeting = true }
        )
    }
}
