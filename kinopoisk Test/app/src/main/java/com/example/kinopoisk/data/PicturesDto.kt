package com.example.kinopoisk.data

import com.example.kinopoisk.entity.Item
import com.example.kinopoisk.entity.Pictures

class PicturesDto(
    override val items: List<Item>,
    override val total: Int,
    override val totalPages: Int
) : Pictures