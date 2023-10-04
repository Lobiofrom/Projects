package com.example.coctails.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.coctails.R
import com.example.coctails.utils.extractUri

@SuppressLint("MutableCollectionMutableState")
@Composable
fun AddCocktail(
    onIconClicked: () -> Unit,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    viewModel: MyViewModel
) {
    val context = LocalContext.current
    var showIngredientDialog by remember {
        mutableStateOf(false)
    }
    val ingredientList by remember {
        mutableStateOf(mutableStateListOf<String>())
    }
    var fileUri by remember {
        mutableStateOf("")
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val getContent =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            imageUri = it
        }

    if (imageUri != null) {
        LaunchedEffect(Unit) {
            fileUri = extractUri(imageUri, context)
            Log.d("tag", "picture: $fileUri")
        }
    }

    Column {
        Back(onIconClicked)
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(200.dp)
                    .width(200.dp)
            ) {
                if (fileUri.isEmpty()) {

                    IconButton(
                        onClick = { getContent.launch("image/*") },
                        modifier = Modifier
                            .paint(painter = painterResource(id = R.drawable.img_6))
                            .align(Alignment.Center)
                    ) {}
                    Image(
                        painter = painterResource(id = R.drawable.img_7),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                } else {
                    val painter = rememberAsyncImagePainter(model = fileUri)
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxSize()
                            .clip(shape = RoundedCornerShape(30.dp))
                    )
                }
            }

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

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = { showIngredientDialog = true },
                    modifier = Modifier
                        .paint(painterResource(id = R.drawable.img_3))
                ) {}
                if (ingredientList.isEmpty()) {
                    Text(
                        text = "Add Ingredients",
                        modifier = Modifier.align(Alignment.CenterVertically),
                        color = Color.Red
                    )
                } else {
                    LazyRow(
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        items(ingredientList) {
                            ItemIngredient(ingredient = it) { ingredientList.remove(it) }
                        }
                    }
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
                onClick = {
                    if (name.isEmpty() || ingredientList.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Add cocktail name and ingredients!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        onSaveClick()
                        viewModel.addRecipe(name, description, recipe, ingredientList, fileUri)
                        Toast.makeText(context, "Cocktail saved!", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
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

    if (showIngredientDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        ) {
            Column(
                modifier = Modifier
                    .height(250.dp)
                    .align(Alignment.Center)
                    .padding(start = 16.dp, end = 16.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(15.dp),
                    )
            ) {
                Text(
                    text = "Add ingredient",
                    fontSize = 32.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                var ingredient by rememberSaveable {
                    mutableStateOf("")
                }

                OutlinedTextFieldBackground(Color.White) {
                    OutlinedTextField(
                        value = ingredient,
                        onValueChange = {
                            ingredient = it
                        },
                        label = {
                            if (ingredient.isEmpty()) {
                                Text(
                                    text = "Ingredient name",
                                    color = Color.Red
                                )
                            }
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 20.sp
                        ),
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .padding(start = 15.dp)
                    )
                }
                Button(
                    onClick = {
                        if (ingredient.isEmpty()) {
                            Toast.makeText(context, "Enter ingredient name!", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            ingredientList.add(ingredient)
                            Toast.makeText(context, "Ingredient saved!", Toast.LENGTH_SHORT).show()
                            showIngredientDialog = false
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 16.dp, end = 16.dp)
                ) {
                    Text(text = "Save")
                }
                Button(
                    onClick = { showIngredientDialog = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 16.dp, end = 16.dp)
                ) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AddCocktailPreview() {
//    CoctailsTheme {
//        val onIconClicked: () -> Unit = {}
//        val onCancelClick: () -> Unit = {}
//        val onSaveClick: () -> Unit = {}
//
//        AddCocktail(
//            onIconClicked = onIconClicked,
//            onCancelClick = onCancelClick,
//            onSaveClick = onSaveClick
//        )
//    }
//}
