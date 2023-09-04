package com.example.kinopoisk.entity

data class Episode(
    val episodeNumber: Int,
    val nameEn: String,
    val nameRu: String,
    val releaseDate: String,
    val seasonNumber: Int,
    val synopsis: Any
)