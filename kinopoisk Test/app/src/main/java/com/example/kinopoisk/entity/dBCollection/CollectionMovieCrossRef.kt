package com.example.kinopoisk.entity.dBCollection

import androidx.room.Entity

@Entity(primaryKeys = ["collectionName", "movieId"])
data class CollectionMovieCrossRef(
    val collectionName: String,
    val movieId: Int
)