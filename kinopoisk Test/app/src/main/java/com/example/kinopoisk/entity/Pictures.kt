package com.example.kinopoisk.entity

interface Pictures {
    val items: List<Item>
    val total: Int
    val totalPages: Int
}