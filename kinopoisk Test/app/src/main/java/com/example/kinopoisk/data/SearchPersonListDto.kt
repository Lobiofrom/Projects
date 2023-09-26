package com.example.kinopoisk.data

import com.example.kinopoisk.entity.SearchPersonList
import com.example.kinopoisk.entity.StaffItem

class SearchPersonListDto(
    override val items: List<StaffItem>,
    override val total: Int
) : SearchPersonList
