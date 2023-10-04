package com.example.logistics.data

data class Task(
    val title: String,
    val description: String,
    var isAccepted: Boolean = false,
    var mistakes: MutableList<Int>,
    var photoList: MutableList<String>
)
