package com.example.hotels.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Room
import com.example.domain.useCases.GetRoomsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomsViewModel @Inject constructor(
    private val getRoomsUseCase: GetRoomsUseCase
) : ViewModel() {
    private var _rooms = MutableStateFlow<List<Room>>(emptyList())
    val rooms = _rooms.asStateFlow()

    init {
        viewModelScope.launch {
            _rooms.value = getRoomsUseCase.execute()
        }
    }
}

@Suppress("UNCHECKED_CAST")
class RoomsViewModelFactory @Inject constructor(
    private val roomsViewModel: RoomsViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return roomsViewModel as T
    }
}