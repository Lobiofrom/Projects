package com.example.data.dto

import com.example.domain.models.Booking

class BookingDto(
    val arrival_country: String,
    val departure: String,
    val fuel_charge: Int,

    val horating: Int,
    val hotel_adress: String,
    val hotel_name: String,
    val id: Int,
    val number_of_nights: Int,
    val nutrition: String,
    val rating_name: String,
    val room: String,
    val service_charge: Int,
    val tour_date_start: String,
    val tour_date_stop: String,
    val tour_price: Int
)

fun BookingDto.toBooking() = Booking(
    arrival_country = arrival_country,
    departure = departure,
    fuel_charge = fuel_charge,
    horating = horating,
    hotel_adress = hotel_adress,
    hotel_name = hotel_name,
    rating_name = rating_name,
    id = id,
    number_of_nights = number_of_nights,
    nutrition = nutrition,
    room = room,
    service_charge = service_charge,
    tour_date_start = tour_date_start,
    tour_date_stop = tour_date_stop,
    tour_price = tour_price
)