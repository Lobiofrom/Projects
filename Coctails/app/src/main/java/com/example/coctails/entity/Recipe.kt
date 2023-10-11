package com.example.coctails.entity

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "recipe")
    val recipe: String?,

    @ColumnInfo(name = "ingredients")
    val ingredients: SnapshotStateList<String>?
)
