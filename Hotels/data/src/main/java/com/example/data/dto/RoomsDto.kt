package com.example.data.dto

import com.example.domain.models.Room
import com.example.domain.models.Rooms

data class RoomsDto(
    val rooms: List<Room>
)
fun RoomsDto.toRooms() = Rooms(
    rooms = rooms
)
