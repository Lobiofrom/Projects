package com.example.feature_search.data.mappers

import com.example.api.GetCharacterQuery
import com.example.feature_search.domain.models.FoundCharacter

fun GetCharacterQuery.Result.toFoundCharacter(): FoundCharacter {
    return FoundCharacter(
        id = id ?: "no id",
        name = name ?: "no name",
        image = image ?: "no image"
    )
}