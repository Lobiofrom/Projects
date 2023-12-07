package com.example.feature_characters.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.feature_characters.viewmodel.CharactersViewModel
import com.example.feature_characters.viewmodel.CharactersViewModelFactory

@Composable
fun Character(
    id: String,
    viewModel: CharactersViewModel = viewModel(
        factory = CharactersViewModelFactory()
    ),
) {
    viewModel.getCharacter(id)

    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
                    model = state.character?.image,
                    contentDescription = null,
                )
                state.character?.name?.let {
                    Text(
                        text = it,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 16.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                state.character?.status?.let {
                    Text(
                        text = it,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(end = 36.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Text(
                text = "gender: ${state.character?.gender}",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 16.dp),
            )

            Text(
                text = "origin: ${state.character?.origin}",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 16.dp),
            )
            Text(
                text = "location: ${state.character?.location}",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 16.dp),
            )
            Text(
                text = "Episodes:",
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                fontStyle = FontStyle.Italic
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 88.dp, start = 16.dp)
            ) {
                state.character?.let {
                    items(it.episode) { episode ->
                        Text(text = episode)
                    }
                }
            }
        }
    }
}