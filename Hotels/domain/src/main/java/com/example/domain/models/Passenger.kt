package com.example.domain.models

data class Passenger(
    val text: String = "Турист",
    val touristCount: Int = 1,
    var name: String = "",
    var surname: String = "",
    var birthdate: String = "",
    var nationality: String = "",
    var passportNumber: String = "",
    var passportDate: String = "",
    var hasEmptyProperty: Boolean = true,
) {
    init {
        hasEmptyProperty = listOf(
            name,
            surname,
            birthdate,
            nationality,
            passportNumber,
            passportDate
        ).contains("")
    }
}
