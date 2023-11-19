package com.example.data.dto

import com.example.domain.models.AboutTheHotel
import com.example.domain.models.Hotel

data class HotelDto(
    val about_the_hotel: AboutTheHotel,
    val adress: String,
    val id: Int,
    val image_urls: List<String>,
    val minimal_price: Int,
    val name: String,
    val price_for_it: String,
    val rating: Int,
    val rating_name: String
)
fun HotelDto.toHotel() = Hotel(
    id = id,
    name = name,
    adress = adress,
    minimal_price = minimal_price,
    price_for_it = price_for_it,
    rating = rating,
    rating_name = rating_name,
    image_urls = image_urls,
    about_the_hotel = about_the_hotel
)
