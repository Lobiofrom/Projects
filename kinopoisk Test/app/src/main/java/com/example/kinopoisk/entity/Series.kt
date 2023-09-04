package com.example.kinopoisk.entity

interface Series {
    val items: List<Season>
    val total: Int
}