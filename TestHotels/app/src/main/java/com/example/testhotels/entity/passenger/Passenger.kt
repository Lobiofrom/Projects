package com.example.testhotels.entity.passenger

data class Passenger(
    val text: String = "Турист",
    val touristCount: Int = 1,
    val name: String = "",
    val nameError: Boolean = false,
    val surname: String = "",
    val surnameError: Boolean = false,
    val birthdate: String = "",
    val birthdayError: Boolean = false,
    val nationality: String = "",
    val nationalityError: Boolean = false,
    val passportNumber: String = "",
    val foreignPassportNumberError: Boolean = false,
    val passportDate: String = "",
    val foreignPassportDateError: Boolean = false,
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