package com.example.coctails.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun MyApp(
    viewModel: MyViewModel
) {
    var showGreeting by remember {
        mutableStateOf(true)
    }

    if (showGreeting) {
        var isVisible by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(Unit) {
            delay(10)
            isVisible = true
        }
        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier
                .fillMaxWidth(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Greeting(
                onContinueClicked = { showGreeting = false },
                viewModel
            )
        }
    } else {
        var isVisible by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(Unit) {
            delay(10)
            isVisible = true
        }
        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier
                .fillMaxWidth(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            AddCocktail(
                recipe = null,
                onIconClicked = { showGreeting = true },
                onCancelClick = { showGreeting = true },
                onSaveClick = { showGreeting = true },
                viewModel = viewModel
            )
        }
    }
}
