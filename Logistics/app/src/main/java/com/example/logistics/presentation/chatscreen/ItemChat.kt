package com.example.logistics.presentation.chatscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logistics.data.Chat

@Composable
fun ItemChat(
    chat: Chat,
    onItemClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = chat.picture),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .height(100.dp)
                    .width(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = chat.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                chat.messages.lastOrNull()?.let {
                    Text(
                        text = it.text,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(top = 6.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewItemChat() {
//    LogisticsTheme {
//        ItemChat()
//    }
//}