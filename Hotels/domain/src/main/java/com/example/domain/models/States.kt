package com.example.domain.models

sealed class States {
    data object Loading : States()
    data object Success : States()
}