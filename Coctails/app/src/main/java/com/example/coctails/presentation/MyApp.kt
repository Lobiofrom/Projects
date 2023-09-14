package com.example.coctails.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun MyApp(
    viewModel: MyViewModel
) {
    var showGreeting by remember {
        mutableStateOf(true)
    }

    if (showGreeting) {
        Greeting(
            onContinueClicked = { showGreeting = false },
            viewModel
        )
    } else {
        AddCocktail(
            onIconClicked = { showGreeting = true },
            onCancelClick = { showGreeting = true },
            onSaveClick = { showGreeting = true },
            viewModel
        )
    }
}
