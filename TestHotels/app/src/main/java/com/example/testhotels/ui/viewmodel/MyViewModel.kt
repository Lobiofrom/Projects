package com.example.testhotels.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.testhotels.data.BookingDto
import com.example.testhotels.data.HotelDto
import com.example.testhotels.data.State
import com.example.testhotels.domain.UseCase
import com.example.testhotels.entity.room.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel(
    private val useCase: UseCase
) : ViewModel() {

    private var _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private var _hotel = MutableStateFlow<HotelDto?>(null)
    val hotel = _hotel.asStateFlow()

    private var _rooms = MutableStateFlow<List<Room>>(emptyList())
    val rooms = _rooms.asStateFlow()

    private var _booking = MutableStateFlow<BookingDto?>(null)
    val booking = _booking.asStateFlow()

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
                val rooms = useCase.executeRooms()
                _rooms.value = rooms
                _state.value = State.Success
            } catch (e: Exception) {
                _state.value = State.Error
            }
        }
    }

    fun getBooking() {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val booking = useCase.executeBooking()
                _booking.value = booking
                _state.value = State.Success
            } catch (e: Exception) {
                _state.value = State.Error
            }
        }
    }
}

class MyViewModelFactory(
    private val viewModel: MyViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}