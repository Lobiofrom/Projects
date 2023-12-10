package com.example.feature_search.domain.models

data class FoundLocation(
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Resident>
)
