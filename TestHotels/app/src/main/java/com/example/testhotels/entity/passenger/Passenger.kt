package com.example.testhotels.entity.passenger

import android.text.Editable

data class Passenger(
    val text: String,
    val touristCount: Int,
    val name: Editable?,
    val surname: Editable?,
    val birthdate: Editable?,
    val nationality: Editable?,
    val passportN: Editable?,
    val passportTime: Editable?
)