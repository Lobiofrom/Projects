package com.example.kinopoisk.data

import com.example.kinopoisk.entity.Season
import com.example.kinopoisk.entity.Series

class SeriesDto(
    override val items: List<Season>,
    override val total: Int
) : Series