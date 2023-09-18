package com.example.coctails.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coctails.R
import com.example.coctails.entity.Recipe

@Composable
fun Item(
    recipe: Recipe,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(140.dp)
            .height(140.dp)
            .padding(10.dp)
            .clickable(
                onClick = onItemClick
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.oldfashioned),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(30.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = recipe.title,
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}
//
//@Preview
//@Composable
//fun PreviewItem() {
//    CoctailsTheme {
//        Item()
//    }
//}