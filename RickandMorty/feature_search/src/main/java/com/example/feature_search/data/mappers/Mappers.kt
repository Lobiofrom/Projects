package com.example.feature_search.data.mappers

import com.example.api.GetCharacterQuery
import com.example.api.GetLocationQuery
import com.example.feature_search.domain.models.FoundCharacter
import com.example.feature_search.domain.models.FoundLocation
import com.example.feature_search.domain.models.Resident

fun GetCharacterQuery.Result.toFoundCharacter(): FoundCharacter {
    return FoundCharacter(
        id = id ?: "no id",
        name = name ?: "no name",
        image = image ?: "no image"
    )
}

fun GetLocationQuery.Result.toFoundLocation(): FoundLocation {
    return FoundLocation(
        name = name ?: "no name",
        type = type ?: "no type",
        dimension = dimension ?: "no dimension",
        residents = residents.mapNotNull {
            Resident(
                id = it?.id ?: "no id",
                image = it?.image ?: "no image"
            )
        }
    )
}