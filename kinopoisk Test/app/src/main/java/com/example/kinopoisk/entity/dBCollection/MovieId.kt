package com.example.kinopoisk.entity.dBCollection

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(
    tableName = "movies",
    foreignKeys = [ForeignKey(
        entity = Collection::class,
        parentColumns = ["collectionId"],
        childColumns = ["collectionId"]
    )]
)
data class MovieId(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val collectionId: Long,
    val movieId: Int
)
