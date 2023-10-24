package com.example.kinopoisk.entity.dBCollection

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CollectionWithMovies(
    @Embedded val collection: Collection,
    @Relation(
        parentColumn = "collectionName",
        entityColumn = "movieId",
        associateBy = Junction(CollectionMovieCrossRef::class)
    )
    val movies: List<MovieId>
)
