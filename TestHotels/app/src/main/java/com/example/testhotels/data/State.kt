package com.example.testhotels.data

sealed class State {
    data object Success : State()
    data object Error : State()
    data object Loading : State()
}