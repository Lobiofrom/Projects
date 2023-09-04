package com.example.testhotels.data

import com.example.testhotels.entity.AboutTheHotel
import com.example.testhotels.entity.Hotel

class HotelDto(
    override val about_the_hotel: AboutTheHotel,
    override val adress: String,
    override val id: Int,
    override val image_urls: List<String>,
    override val minimal_price: Int,
    override val name: String,
    override val price_for_it: String,
    override val rating: Int,
    override val rating_name: String
) : Hotel