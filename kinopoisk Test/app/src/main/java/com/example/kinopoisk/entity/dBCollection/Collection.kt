package com.example.kinopoisk.entity.dBCollection

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collections")
data class Collection(
    @PrimaryKey(autoGenerate = true) val collectionId: Long = 0,
    val collectionName: String
)
