package com.example.coctails.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coctails.R
import com.example.coctails.entity.Recipe
import kotlinx.coroutines.delay


@Composable
fun Greeting(
    onContinueClicked: () -> Unit,
    viewModel: MyViewModel
) {

    var recipeList by remember {
        mutableStateOf(emptyList<Recipe>())
    }

    var showDetailScreen by remember {
        mutableStateOf(false)
    }

    var recipe by remember {
        mutableStateOf<Recipe?>(null)
    }

    LaunchedEffect(Unit) {
        viewModel.allRecipes.collect {
            recipeList = it
        }

    }

    if (!showDetailScreen) {
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
            if (recipeList.isEmpty()) {
                Column(modifier = Modifier.padding(36.dp)) {

                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = null
                    )

                    Text(
                        text = "My Cocktails",
                        fontSize = 36.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(46.dp))
                    Text(
                        text = "Add your first cocktail here",
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(46.dp))
                    Image(
                        painter = painterResource(id = R.drawable.img_1),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(46.dp))
                    IconButton(
                        onClick = onContinueClicked,
                        modifier = Modifier
                            .paint(painterResource(id = R.drawable.img_2))
                            .align(Alignment.CenterHorizontally),
                    ) {}
                }
            } else {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(6.dp)
                    ) {
                        Text(
                            text = "My Cocktails",
                            fontSize = 36.sp,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2)
                        ) {
                            items(recipeList) {
                                Item(recipe = it) {
                                    showDetailScreen = true
                                    recipe = it
                                }
                            }
                        }
                    }
                    IconButton(
                        onClick = onContinueClicked,
                        modifier = Modifier
                            .paint(painterResource(id = R.drawable.img_2))
                            .align(Alignment.BottomCenter)
                            .padding(26.dp),
                    ) {}
                }
            }
        }
    } else {
        var isVisible2 by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(Unit) {
            delay(10)
            isVisible2 = true
        }

        AnimatedVisibility(
            visible = isVisible2,
            modifier = Modifier
                .fillMaxWidth(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            recipe?.let {
                DetailScreen(
                    recipe = it, viewModel = viewModel
                ) {
                    showDetailScreen = false
                }
            }
        }
    }
}

