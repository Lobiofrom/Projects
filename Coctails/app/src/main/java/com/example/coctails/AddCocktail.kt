package com.example.coctails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coctails.entity.Ingredient
import com.example.coctails.ui.theme.CoctailsTheme

@Composable
fun AddCocktail(
    onIconClicked: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column {
        Back(onIconClicked)
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            var name by rememberSaveable { mutableStateOf("") }

            OutlinedTextFieldBackground(Color.White) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        if (name.isEmpty()) {
                            Text(
                                text = "Cocktail name",
                                color = Color.Red
                            )
                        }
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(50.dp),
                )
            }

            var description by rememberSaveable { mutableStateOf("") }

            OutlinedTextFieldBackground(Color.White) {
                OutlinedTextField(
                    value = description,
                    onValueChange = {
                        description = it
                    },
                    label = {
                        Text(
                            text = "Description",
                            color = Color(0xFF79747E)
                        )
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                        .height(174.dp),
                    shape = RoundedCornerShape(50.dp),
                )
            }

            Text(
                text = "Optional field",
                color = Color(0xFF79747E),
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 46.dp)
            )

            val ingredientList by remember {
                mutableStateOf(emptyList<Ingredient>())
            }

            Row {
                LazyRow {
                    items(ingredientList) {
                    }
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .paint(painterResource(id = R.drawable.img_3))
                ) {
                }
            }
            var recipe by rememberSaveable { mutableStateOf("") }

            OutlinedTextFieldBackground(Color.White) {
                OutlinedTextField(
                    value = recipe,
                    onValueChange = {
                        recipe = it
                    },
                    label = {
                        Text(
                            text = "Recipe",
                            color = Color(0xFF79747E)
                        )
                    },
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                        .height(174.dp),
                    shape = RoundedCornerShape(50.dp),
                )
            }

            Text(
                text = "Optional field",
                color = Color(0xFF79747E),
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 46.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Save")
            }
            Button(
                onClick = onCancelClick,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Cancel")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCocktailPreview() {
    CoctailsTheme {
        val onIconClicked: () -> Unit = {}
        val onCancelClick: () -> Unit = {}
        AddCocktail(
            onIconClicked = onIconClicked,
            onCancelClick = onCancelClick
        )
    }
}
