package com.example.logistics.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.logistics.R
import com.example.logistics.data.Chat
import com.example.logistics.data.Task
import com.example.logistics.data.UserMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyViewModel : ViewModel() {

    private val _items = MutableStateFlow(
        listOf(
            Task(
                title = "Задание 1",
                description = "Я самое лучшее задание!!!!",
                isAccepted = false,
                mistakes = mutableListOf(),
                photoList = mutableListOf()
            ),
            Task(
                title = "Задание 2",
                description = "Нет, я намного лучше!",
                isAccepted = false,
                mistakes = mutableListOf(),
                photoList = mutableListOf()
            ),
            Task(
                title = "Задание 3",
                description = "Я самый крутой, возьмите меня!",
                isAccepted = false,
                mistakes = mutableListOf(),
                photoList = mutableListOf()
            )
        )
    )

    val items = _items.asStateFlow()

    private val _chats = MutableStateFlow(
        listOf(
            Chat(
                picture = R.drawable.photo,
                name = "Denis",
                messages = listOf(
                    UserMessage(
                        username = "Я",
                        text = "Hello",
                        isOwnMessage = true
                    ),
                    UserMessage(
                        username = "Denis",
                        text = "Hi!",
                        isOwnMessage = false
                    ),
                    UserMessage(
                        username = "Я",
                        text = "How are you?",
                        isOwnMessage = true
                    )
                )
            ),
            Chat(
                picture = R.drawable.photo_2,
                name = "Ivan",
                messages = listOf(
                    UserMessage(
                        username = "Я",
                        text = "Привет",
                        isOwnMessage = true
                    ),
                    UserMessage(
                        username = "Ivan",
                        text = "Хай",
                        isOwnMessage = false
                    ),
                    UserMessage(
                        username = "Я",
                        text = "Как дела?",
                        isOwnMessage = true
                    ),
                    UserMessage(
                        username = "Ivan",
                        text = "Нормально, твои как? У меня все хорошо",
                        isOwnMessage = false
                    )
                )
            )
        )
    )
    val chats = _chats.asStateFlow()
}