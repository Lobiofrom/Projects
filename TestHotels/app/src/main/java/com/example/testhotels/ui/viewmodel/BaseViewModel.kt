package com.example.testhotels.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State : Any>(initialState: State) : ViewModel() {

    private val _viewStates = MutableStateFlow(initialState)

    protected var viewState: State
        get() = _viewStates.value
        set(value) {
            _viewStates.value = value
        }

    fun viewStates() = _viewStates.asStateFlow()

}