package com.example.logistics.presentation.chatscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.logistics.data.Chat

@Composable
fun MessagesScreen(
    chat: Chat,
    onBackClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(bottom = 46.dp)
        ) {
            Text(
                text = "Назад к чатам",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable(onClick = onBackClick)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    reverseLayout = true
                ) {
                    item {
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                    items(chat.messages) {
                        val isOwnMessage = it.isOwnMessage
                        Box(
                            contentAlignment = if (isOwnMessage) {
                                Alignment.CenterEnd
                            } else {
                                Alignment.CenterStart
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(200.dp)
                                    .drawBehind {
                                        val corner = 10.dp.toPx()
                                        val height = 20.dp.toPx()
                                        val width = 25.dp.toPx()
                                        val path = Path().apply {
                                            if (isOwnMessage) {
                                                moveTo(size.width, size.height - corner)
                                                lineTo(size.width, size.height + height)
                                                lineTo(size.width - width, size.height - corner)
                                                close()
                                            } else {
                                                moveTo(0f, size.height - corner)
                                                lineTo(0f, size.height + height)
                                                lineTo(width, size.height - corner)
                                                close()
                                            }
                                        }
                                        drawPath(
                                            path = path,
                                            color = if (isOwnMessage) Color.Green else Color.DarkGray
                                        )
                                    }
                                    .background(
                                        color = if (isOwnMessage) Color.Green else Color.DarkGray,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = it.username,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = it.text,
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    var message by rememberSaveable { mutableStateOf("") }

                    TextField(
                        value = message,
                        onValueChange = {
                            message = it
                        },
                        placeholder = {
                            Text(text = "Введите сообщение")
                        },
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = { /*TODO*/ },

                        ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null)
                    }
                }
            }
        }
    }
}
//
//@Preview
//@Composable
//fun PreviewChat() {
//    LogisticsTheme {
//        MessagesScreen()
//    }
//}