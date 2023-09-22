package com.example.testhotels.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.testhotels.data.BookingDto
import com.example.testhotels.data.BookingState
import com.example.testhotels.data.HotelDto
import com.example.testhotels.data.State
import com.example.testhotels.domain.UseCase
import com.example.testhotels.entity.passenger.Passenger
import com.example.testhotels.entity.passenger.TextField
import com.example.testhotels.entity.room.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel(
    private val useCase: UseCase
) : BaseViewModel<BookingState>(BookingState()) {

    private var _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private var _hotel = MutableStateFlow<HotelDto?>(null)
    val hotel = _hotel.asStateFlow()

    private var _rooms = MutableStateFlow<List<Room>>(emptyList())
    val rooms = _rooms.asStateFlow()

    private var _booking = MutableStateFlow<BookingDto?>(null)
    val booking = _booking.asStateFlow()

    fun addPassenger() {
        val newList = viewState.passengerList.toMutableList()
        newList.add(
            Passenger(
                text = "Турист",
                touristCount = viewState.passengerList.last().touristCount + 1,
                name = "",
                surname = "",
                birthdate = "",
                nationality = "",
                passportNumber = "",
                passportDate = ""
            )
        )
        viewState = viewState.copy(
            passengerList = newList
        )
    }

    fun onTextChange(textField: TextField, touristIndex: Int) {
        viewModelScope.launch {
            val newTouristList = viewState.passengerList.toMutableList()
            if (touristIndex >= 0 && touristIndex < newTouristList.size) {

                var newTourist = newTouristList[touristIndex]
                when (textField) {
                    TextField.NAME -> {
                        newTourist = newTourist.copy(name = textField.property)
                    }

                    TextField.SURNAME -> {
                        newTourist = newTourist.copy(surname = textField.property)
                    }

                    TextField.BIRTHDAY -> {
                        newTourist = newTourist.copy(birthdate = textField.property)
                    }

                    TextField.NATIONALITY -> {
                        newTourist = newTourist.copy(nationality = textField.property)
                    }

                    TextField.PASSPORT_NUMBER -> {
                        newTourist = newTourist.copy(passportNumber = textField.property)
                    }

                    TextField.PASSPORT_DATE -> {
                        newTourist = newTourist.copy(passportDate = textField.property)
                    }
                }
                newTouristList[touristIndex] = newTourist
                viewState = viewState.copy(
                    passengerList = newTouristList
                )
            }
        }
    }

    fun checkEmptyFields() {
        viewModelScope.launch {
            val newTouristList = viewState.passengerList.toMutableList()
            viewState.passengerList.forEachIndexed { index, tourist ->
                var newTourist = tourist
                newTourist = newTourist.copy(nameError = newTourist.name.isEmpty())
                newTourist = newTourist.copy(surnameError = newTourist.surname.isEmpty())
                newTourist = newTourist.copy(birthdayError = newTourist.birthdate.isEmpty())
                newTourist = newTourist.copy(nationalityError = newTourist.nationality.isEmpty())
                newTourist =
                    newTourist.copy(foreignPassportNumberError = newTourist.passportNumber.isEmpty())
                newTourist =
                    newTourist.copy(foreignPassportDateError = newTourist.passportDate.isEmpty())
                newTouristList[index] = newTourist

            }
            viewState = viewState.copy(
                passengerList = newTouristList
            )
        }
    }

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