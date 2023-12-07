package com.example.feature_locations.presentation.domain.model

data class Location(
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Resident>
)
