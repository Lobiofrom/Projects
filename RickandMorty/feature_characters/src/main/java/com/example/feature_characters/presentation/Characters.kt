package com.example.feature_characters.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.feature_characters.viewmodel.CharactersViewModel


private val scrollState = LazyListState(0)

@Composable
fun Characters(
    charactersViewModel: CharactersViewModel,
    navController: NavController
) {
    val list = charactersViewModel.list.collectAsLazyPagingItems()

    var error by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        state = scrollState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(list.itemCount) { index ->
            list[index]?.let { character ->
                Item(
                    character = character,
                    onClick = {
                        navController.navigate("character/${character.id}") {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
        if (list.itemCount == 0) {
            error = true
        } else if (list.itemCount > 0) {
            error = false
        }

        when {
            list.loadState.refresh is LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier.fillParentMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            list.loadState.append is LoadState.Loading -> {
                item {
                    CircularProgressIndicator()
                }
            }
        }
    }
    if (error && list.loadState.refresh !is LoadState.Loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Connection Error",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 20.sp
            )
            Button(
                onClick = { list.refresh() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 150.dp, end = 16.dp),
            ) {
                Text(text = "Retry")
            }
        }
    }
}
