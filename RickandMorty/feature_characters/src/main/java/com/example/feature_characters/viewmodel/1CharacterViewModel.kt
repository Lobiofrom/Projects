package com.example.feature_characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.api.data.apollo
import com.example.feature_characters.data.RepoImpl
import com.example.feature_characters.domain.model.Character
import com.example.feature_characters.domain.usecases.GetCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    val state = _state.asStateFlow()
    fun getCharacter(id: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    character = getCharacterUseCase.execute(id),
                    isLoading = false
                )
            }
        }
    }

    data class CharacterState(
        val character: Character? = null,
        val isLoading: Boolean = false,
    )
}

@Suppress("UNCHECKED_CAST")
class CharacterViewModelFactory : ViewModelProvider.Factory {
    private val repository = RepoImpl(apollo)
    private val getCharacterUseCase = GetCharacterUseCase(repository)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(getCharacterUseCase) as T
    }
}