package com.example.kinopoisk.entity

interface MovieSelection {
    val items: List<Movie>
    val total: Int
    val totalPages: Int
}