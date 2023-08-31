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
    val description: String,
    val general: Boolean,
    val professionKey: String,

    val completed: Boolean,
    val coverUrl: String,
    val editorAnnotation: Any,
    val endYear: Any,
    val has3D: Boolean,
    val hasImax: Boolean,
    val isTicketsAvailable: Boolean,
    val kinopoiskHDId: String,
    val lastSync: String,
    val logoUrl: Any,
    val productionStatus: Any,
    val ratingAgeLimits: String,
    val ratingAwait: Double,
    val ratingAwaitCount: Int,
    val ratingFilmCritics: Double,
    val ratingFilmCriticsVoteCount: Int,
    val ratingGoodReview: Double,
    val ratingGoodReviewVoteCount: Int,
    val ratingImdb: Double,
    val ratingImdbVoteCount: Int,
    val ratingKinopoiskVoteCount: Int,
    val ratingMpaa: String,
    val ratingRfCritics: Double,
    val ratingRfCriticsVoteCount: Int,
    val reviewsCount: Int,
    val serial: Boolean,
    val shortDescription: String,
    val shortFilm: Boolean,
    val slogan: String,
    val startYear: Any,
    val webUrl: String,
)