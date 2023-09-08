package com.example.testhotels.data

import com.example.testhotels.entity.booking.Booking

class BookingDto(
    override val arrival_country: String,
    override val departure: String,
    override val fuel_charge: Int,
    override val horating: Int,
    override val hotel_adress: String,
    override val hotel_name: String,
    override val id: Int,
    override val number_of_nights: Int,
    override val nutrition: String,
    override val rating_name: String,
    override val room: String,
    override val service_charge: Int,
    override val tour_date_start: String,
    override val tour_date_stop: String,
    override val tour_price: Int
) : Booking