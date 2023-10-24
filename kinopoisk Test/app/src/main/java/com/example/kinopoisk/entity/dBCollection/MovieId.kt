package com.example.kinopoisk.entity.dBCollection

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieId(
    @PrimaryKey
    val movieId: Int
)
