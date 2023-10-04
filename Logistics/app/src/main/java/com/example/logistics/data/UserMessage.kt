package com.example.logistics.data

data class UserMessage(
    val text: String,
    val username: String,
    val isOwnMessage: Boolean = false
)
