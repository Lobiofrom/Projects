package com.example.feature_locations.presentation.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.feature_locations.presentation.domain.model.Location

@Composable
fun Item(
    location: Location,
    navController: NavController,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier.padding(6.dp)
        ) {
            Text(
                text = location.type + ":",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .alignByBaseline(),
                fontSize = 14.sp
            )
            Text(
                text = location.name, modifier = Modifier
                    .padding(start = 16.dp)
                    .alignByBaseline(),
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier.padding(6.dp)
        ) {
            Text(
                text = "Dimension:",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .alignByBaseline()
                    .alignByBaseline(),
                fontSize = 14.sp
            )
            Text(
                text = location.dimension,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .alignByBaseline(),
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = "Residents:",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic
        )
        LazyRow(
            modifier = Modifier.padding(6.dp)
        ) {
            items(location.residents) {
                Spacer(modifier = Modifier.width(6.dp))
                AsyncImage(
                    model = it.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate("character/${it.id}")
                        }
                )
            }
        }
    }
}