package com.example.coctails.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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


@Composable
fun Greeting(
    onContinueClicked: () -> Unit,
    viewModel: MyViewModel
) {

    var recipeList by remember {
        mutableStateOf(emptyList<Recipe>())
    }

    LaunchedEffect(Unit) {
        viewModel.allRecipes.collect {
            recipeList = it
        }

    }

    Column(modifier = Modifier.padding(36.dp)) {
        if (recipeList.isEmpty()) {
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
        } else {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column {

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
                            Item(recipe = it)
                        }
                    }
                }
            }
        }
        IconButton(
            onClick = onContinueClicked,
            modifier = Modifier
                .paint(painterResource(id = R.drawable.img_2))
                .align(Alignment.CenterHorizontally),
        ) {

        }
    }
}
