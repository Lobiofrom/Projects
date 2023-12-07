package com.example.feature_characters.data

import com.example.api.CharacterQuery
import com.example.api.CharactersQuery
import com.example.feature_characters.domain.model.Character
import com.example.feature_characters.domain.model.SimpleCharacter

fun CharacterQuery.Character.toCharacter(): Character {

    return Character(
        name = name ?: "no name",
        status = status ?: "no status",
        species = species ?: "no species",
        gender = gender ?: "no gender",
        origin = origin?.name ?: "no origin",
        image = image ?: "no image",
        location = location?.name ?: "no location",
        episode = episode.mapNotNull { it?.name }
    )
}

fun CharactersQuery.Result.toSimpleCharacter(): SimpleCharacter {

    return SimpleCharacter(
        id = id ?: "no id",
        name = name ?: "no name",
        status = status ?: "no status",
        species = species ?: "no species",
        image = image ?: "no image",
        location = location?.name ?: "no location",
    )
}