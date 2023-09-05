package com.example.testhotels.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testhotels.data.HotelDto
import com.example.testhotels.data.State
import com.example.testhotels.domain.UseCase
import com.example.testhotels.entity.room.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MyViewModel : ViewModel() {
    private val useCase = UseCase()

    private var _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private var _hotel = MutableStateFlow<HotelDto?>(null)
    val hotel = _hotel.asStateFlow()

    private var _rooms = MutableStateFlow<List<Room>>(emptyList())
    val rooms = _rooms.asStateFlow()

    fun getHotel() {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val hotel = useCase.executeHotel()
                _hotel.value = hotel
                _state.value = State.Success
            } catch (e: Exception) {
                _state.value = State.Error
            }
        }
    }

    fun getRooms() {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val  rooms = useCase.executeRooms()
                _rooms.value = rooms
                _state.value = State.Success
            } catch (e: Exception) {
                _state.value = State.Error
            }
        }
    }
}