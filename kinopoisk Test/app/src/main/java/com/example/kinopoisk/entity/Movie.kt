package com.example.kinopoisk.entity

data class Movie(
    val countries: List<Country>,
    val ratingKinopoisk: Double,
    val filmId: Int,
    val kinopoiskId: Int,
    val filmLength: String,
    val rating: String?,
    val imdbId: String?,
    val genres: List<Genre>,
    val nameOriginal: String?,
    val nameEn: String?,
    val nameRu: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val premiereRu: String?,
    val year: Int,
    val type: String?,
)