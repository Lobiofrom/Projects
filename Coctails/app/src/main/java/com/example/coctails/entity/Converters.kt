package com.example.coctails.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromIngredientsList(ingredients: List<String>?): String {
        if (ingredients == null) {
            return ""
        }
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(ingredients, type)
    }

    @TypeConverter
    fun toIngredientsList(ingredientsString: String): List<String> {
        if (ingredientsString.isEmpty()) {
            return emptyList()
        }
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(ingredientsString, type)
    }
}
