package com.example.kinopoisk.utils

import androidx.room.TypeConverter
class MovieIdListConverter {
    @TypeConverter
    fun fromList(list: MutableList<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toList(string: String): MutableList<Int> {
        return string.split(",").map { it.toInt() }.toMutableList()
    }
}