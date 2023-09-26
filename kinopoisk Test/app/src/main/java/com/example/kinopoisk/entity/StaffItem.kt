package com.example.kinopoisk.entity

data class StaffItem(
    val kinopoiskId: Int,
    val description: String?,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val professionKey: String,
    val professionText: String,
    val staffId: Int,
    val sex: String,
    val webUrl: String
)