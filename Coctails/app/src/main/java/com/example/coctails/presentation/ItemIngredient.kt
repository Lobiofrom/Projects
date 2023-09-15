package com.example.coctails.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coctails.R
import com.example.coctails.ui.theme.CoctailsTheme

@Composable
fun ItemIngredient(ingredient: String?) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp)
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(5.dp)
        ) {
            Text(
                text = ingredient ?: "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .wrapContentSize()
                    .padding(start = 6.dp)
            )
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp, end = 5.dp)
                    .paint(painterResource(id = R.drawable.img_5))
                    .size(15.dp)
            ) {}
        }
    }
    Spacer(modifier = Modifier.width(8.dp))
}


@Preview(showBackground = true)
@Composable
fun ItemIngredientPreview() {
    CoctailsTheme {
        ItemIngredient(ingredient = "Vodka")
    }
}
