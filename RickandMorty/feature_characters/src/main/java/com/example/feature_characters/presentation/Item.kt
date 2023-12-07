package com.example.feature_characters.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.feature_characters.R
import com.example.feature_characters.domain.model.SimpleCharacter

@Composable
fun Item(
    character: SimpleCharacter,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row {
            AsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = character.name,
                    modifier = Modifier.padding(start = 6.dp),
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier.padding(start = 6.dp),
                ) {
                    Image(
                        painter = painterResource(id = if (character.status == "Alive") R.drawable.status_indicator else R.drawable.dead_indicator),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Text(
                        text = character.status,
                        modifier = Modifier.padding(start = 6.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = character.species,
                        modifier = Modifier.padding(start = 6.dp),
                        fontWeight = FontWeight.Bold

                    )
                }
                Text(
                    text = "Last known location:",
                    modifier = Modifier.padding(start = 6.dp, top = 16.dp),
                    color = MaterialTheme.colorScheme.tertiaryContainer
                )
                Text(
                    text = character.location, modifier = Modifier.padding(start = 6.dp),
                )
            }
        }
    }
}