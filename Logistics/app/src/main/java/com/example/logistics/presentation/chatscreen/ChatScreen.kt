package com.example.logistics.presentation.chatscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.logistics.data.Chat
import com.example.logistics.presentation.viewmodel.MyViewModel

@Composable
fun ChatScreen(
    viewModel: MyViewModel
) {

    var chats by remember {
        mutableStateOf(emptyList<Chat>())
    }

    var chat by remember {
        mutableStateOf<Chat?>(null)
    }

    LaunchedEffect(Unit) {
        viewModel.chats.collect {
            chats = it
        }
    }

    var showChatDetails by remember {
        mutableStateOf(false)
    }

    if (!showChatDetails) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(bottom = 46.dp)
            ) {
                Text(
                    text = "Чат",
                    modifier = Modifier.padding(16.dp)
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(chats) {
                        ItemChat(chat = it) {
                            showChatDetails = true
                            chat = it
                        }
                    }
                }
            }
        }
    } else {
        MessagesScreen(
            chat = chat!!,
            onBackClick = { showChatDetails = false }
        )
    }
}

//@Preview
//@Composable
//fun PreviewItemChat() {
//    LogisticsTheme {
//        ChatScreen()
//    }
//}