package com.example.hotels.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Hotel
import com.example.domain.models.States
import com.example.domain.useCases.GetHotelUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelViewModel @Inject constructor(
    private val getHotelUseCase: GetHotelUseCase
) : ViewModel() {
    private var _hotel = MutableStateFlow<Hotel?>(null)
    val hotel = _hotel.asStateFlow()

    private var _state = MutableStateFlow<States>(States.Success)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = States.Loading
            _hotel.value = getHotelUseCase.execute()
            _state.value = States.Success
        }
    }
}

@Suppress("UNCHECKED_CAST")
class HotelViewModelFactory @Inject constructor(
    private val hotelViewModel: HotelViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return hotelViewModel as T
    }
}