package com.example.coctails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Greeting(
    name: String,
    onContinueClicked: () -> Unit
    ) {
    Column(modifier = Modifier.padding(36.dp)) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = null
        )

        Text(
            text = name,
            fontSize = 36.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(46.dp))
        Text(
            text = "Add your first cocktail here",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(46.dp))
        Image(
            painter = painterResource(id = R.drawable.img_1),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(46.dp))
        IconButton(
            onClick = onContinueClicked,
            modifier = Modifier
                .paint(painterResource(id = R.drawable.img_2))
                .align(Alignment.CenterHorizontally),
        ) {

        }
    }
}
