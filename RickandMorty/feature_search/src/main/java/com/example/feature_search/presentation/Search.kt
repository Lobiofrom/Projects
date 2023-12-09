package com.example.feature_search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.feature_characters.viewmodel.CharacterViewModel
import com.example.feature_search.VM.FindCharacterVM

@Composable
fun Search(
    findCharacterVM: FindCharacterVM,
    characterViewModel: CharacterViewModel,
    navController: NavController
) {
    var name by rememberSaveable { mutableStateOf("") }
    var status by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }

    val list = findCharacterVM.list.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, top = 6.dp)
        ) {
            Column {
                Text(
                    text = "Search characters",
                    fontStyle = FontStyle.Italic
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text(
                            text = "Character name",
                            color = Color.Red
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    shape = RoundedCornerShape(10.dp),
                )
                OutlinedTextField(
                    value = status,
                    onValueChange = {
                        status = it
                    },
                    label = {
                        Text(
                            text = "Dead or alive?",
                            color = Color.Red
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),
                    shape = RoundedCornerShape(10.dp),
                )

            }
            Button(
                onClick = {
                    findCharacterVM.findCharacter(name, status)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = "Search", fontSize = 13.sp)
            }
        }
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.padding(6.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, top = 6.dp)
        ) {
            Column {
                Text(
                    text = "Search locations",
                    fontStyle = FontStyle.Italic
                )
                OutlinedTextField(
                    value = location,
                    onValueChange = {
                        location = it
                    },
                    label = {
                        Text(
                            text = "Location name",
                            color = Color.Red
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 10.sp
                    ),
                    shape = RoundedCornerShape(10.dp),
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = "Search", fontSize = 13.sp)
            }
        }
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier.padding(6.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            state = rememberLazyGridState(),
            contentPadding = PaddingValues(6.dp)
        ) {
            items(list.itemCount) { index ->
                list[index].let { character ->
                    character?.let {
                        Item(
                            foundCharacter = it,
                            onClick = {
                                characterViewModel.getCharacter(character.id)
                                navController.navigate("character")
                            }
                        )
                    }
                }
            }
        }
    }
}