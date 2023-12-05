package com.example.feature_characters.domain.model

data class Character(
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String,
    val episode: List<String>
)
