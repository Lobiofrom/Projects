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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.feature_characters.viewmodel.CharacterViewModel

@Composable
fun Character(
    state: CharacterViewModel.CharacterState
) {

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
                            .align(Alignment.BottomEnd)
                            .padding(end = 16.dp),
                        fontWeight = FontWeight.Bold,
                        color = if (state.character.status == "Alive") Color.Green else Color.Red
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