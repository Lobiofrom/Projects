package com.example.feature_search.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.feature_search.VM.FindCharacterVM
import com.example.feature_search.VM.FindLocationVM

@Composable
fun Search(
    findCharacterVM: FindCharacterVM, findLocationVM: FindLocationVM, navController: NavController
) {
    var name by rememberSaveable { mutableStateOf("") }
    var status by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }

    val list = findCharacterVM.list.collectAsLazyPagingItems()
    val locationList = findLocationVM.list.collectAsLazyPagingItems()

    var showProgress by remember {
        mutableStateOf(false)
    }

    var showCharacters by remember {
        mutableStateOf(false)
    }
    var showLocations by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier.padding(6.dp),
            shape = CardDefaults.elevatedShape,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, top = 6.dp)
            ) {
                Column {
                    Text(
                        text = "Search characters", fontStyle = FontStyle.Italic
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        label = {
                            Text(
                                text = "Character name", color = MaterialTheme.colorScheme.primary
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 18.sp
                        ),
                        shape = RoundedCornerShape(10.dp),
                    )
                    OutlinedTextField(
                        modifier = Modifier.padding(bottom = 6.dp),
                        value = status,
                        onValueChange = {
                            status = it
                        },
                        label = {
                            Text(
                                text = "Dead or alive?", color = MaterialTheme.colorScheme.primary
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
                        //throw RuntimeException("Test Crash") // Force a crash
                        showProgress = true
                        showCharacters = true
                        showLocations = false
                        findCharacterVM.findCharacter(name, status)
                    },
                    modifier = Modifier
                        .padding(start = 6.dp, bottom = 3.dp)
                        .align(Alignment.Bottom)
                ) {
                    Text(text = "Search", fontSize = 11.sp)
                }
            }
        }
        Card(
            modifier = Modifier.padding(6.dp),
            shape = CardDefaults.elevatedShape,
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, top = 6.dp)
            ) {
                Column {
                    Text(
                        text = "Search locations", fontStyle = FontStyle.Italic
                    )
                    OutlinedTextField(
                        modifier = Modifier.padding(bottom = 6.dp),
                        value = location,
                        onValueChange = {
                            location = it
                        },
                        label = {
                            Text(
                                text = "Location name", color = MaterialTheme.colorScheme.primary
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
                        showProgress = true
                        showLocations = true
                        showCharacters = false
                        findLocationVM.getLocation(location)
                    },
                    modifier = Modifier
                        .padding(start = 6.dp, bottom = 3.dp)
                        .align(Alignment.Bottom)
                ) {
                    Text(text = "Search", fontSize = 11.sp)
                }
            }
        }
        if (showCharacters) {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(3),
                state = rememberLazyGridState(),
                modifier = Modifier.padding(bottom = 90.dp)
            ) {
                items(list.itemCount) { index ->
                    list[index].let { character ->
                        character?.let {
                            Item(foundCharacter = it, onClick = {
                                navController.navigate("character/${character.id}")
                            })
                        }
                    }
                }
            }
        }
        if (showLocations) {
            LazyColumn(
                modifier = Modifier.padding(bottom = 90.dp)
            ) {
                items(locationList.itemCount) { index ->
                    locationList[index].let {
                        if (it != null) {
                            ItemLocation(
                                location = it, navController = navController
                            )
                        }
                    }
                }
            }
        }
        if (showProgress) {
            when {
                list.loadState.refresh is LoadState.Loading -> {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 150.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                list.loadState.append is LoadState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                locationList.loadState.refresh is LoadState.Loading -> {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 150.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                locationList.loadState.append is LoadState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}