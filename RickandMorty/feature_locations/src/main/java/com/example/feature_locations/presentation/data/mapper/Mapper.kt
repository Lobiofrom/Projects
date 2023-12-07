package com.example.feature_locations.presentation.data.mapper

import com.example.api.LocationsQuery
import com.example.feature_locations.presentation.domain.model.Location
import com.example.feature_locations.presentation.domain.model.Resident

fun LocationsQuery.Result.toLocation(): Location {
    return Location(
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