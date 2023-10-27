package com.example.kinopoisk.entity.dBCollection

import androidx.room.Embedded
import androidx.room.Relation

data class CollectionWithMovies(
    @Embedded val collection: Collection,
    @Relation(
        parentColumn = "collectionId",
        entityColumn = "collectionId"
    )
    val movies: List<MovieId>
)
