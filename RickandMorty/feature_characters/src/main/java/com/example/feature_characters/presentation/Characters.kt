package com.example.feature_characters.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.feature_characters.viewmodel.CharactersViewModel

@Composable
fun Characters(
    viewModel: CharactersViewModel
) {
    val list = viewModel.list.collectAsLazyPagingItems()

    val scrollState = ScrollStateHolder.scrollState

    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(list.itemCount) { index ->
            list[index]?.let {
                Item(character = it)
            }
        }

    }
}