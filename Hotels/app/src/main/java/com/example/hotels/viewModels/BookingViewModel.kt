package com.example.hotels.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Booking
import com.example.domain.models.Passenger
import com.example.domain.models.PassengerList
import com.example.domain.models.TextField
import com.example.domain.useCases.GetBookingUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookingViewModel @Inject constructor(
    private val getBookingUseCase: GetBookingUseCase
) : ViewModel() {
    private var _booking = MutableStateFlow<Booking?>(null)
    val booking = _booking.asStateFlow()

    private var _passengerList = MutableStateFlow(PassengerList())
    val passengerList = _passengerList.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    fun addPassenger() {
        viewModelScope.launch {
            val newList = _passengerList.value.passengerList.toMutableList()
            newList.add(
                Passenger(
                    text = "Турист",
                    touristCount = newList.last().touristCount + 1,
                    name = "",
                    surname = "",
                    birthdate = "",
                    nationality = "",
                    passportNumber = "",
                    passportDate = ""
                )
            )
            _passengerList.value = _passengerList.value.copy(passengerList = newList)
        }
    }

    fun onTextChange(textField: TextField, passenger: Passenger) {
        scope.launch {
            val newTouristList = _passengerList.value.passengerList.toMutableList()
            val newTourist = newTouristList.indexOf(passenger)
            if (newTourist != -1) {
                when (textField) {
                    TextField.NAME -> {
                        newTouristList[newTourist] =
                            newTouristList[newTourist].copy(name = textField.property)
                    }

                    TextField.SURNAME -> {
                        newTouristList[newTourist] =
                            newTouristList[newTourist].copy(surname = textField.property)
                    }

                    TextField.BIRTHDAY -> {
                        newTouristList[newTourist] =
                            newTouristList[newTourist].copy(birthdate = textField.property)
                    }

                    TextField.NATIONALITY -> {
                        newTouristList[newTourist] =
                            newTouristList[newTourist].copy(nationality = textField.property)
                    }

                    TextField.PASSPORT_NUMBER -> {
                        newTouristList[newTourist] =
                            newTouristList[newTourist].copy(passportNumber = textField.property)
                    }

                    TextField.PASSPORT_DATE -> {
                        newTouristList[newTourist] =
                            newTouristList[newTourist].copy(passportDate = textField.property)
                    }
                }
                _passengerList.value = _passengerList.value.copy(passengerList = newTouristList)
            }
        }
    }

    init {
        viewModelScope.launch {
            _booking.value = getBookingUseCase.execute()
        }
    }
}

@Suppress("UNCHECKED_CAST")
class BookingViewModelFactory @Inject constructor(
    private val bookingViewModel: BookingViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return bookingViewModel as T
    }
}