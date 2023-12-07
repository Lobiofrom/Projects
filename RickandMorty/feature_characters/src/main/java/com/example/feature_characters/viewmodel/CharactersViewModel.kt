package com.example.feature_characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.api.data.apollo
import com.example.feature_characters.data.RepoImpl
import com.example.feature_characters.domain.model.Character
import com.example.feature_characters.domain.model.SimpleCharacter
import com.example.feature_characters.domain.usecases.GetCharacterUseCase
import com.example.feature_characters.domain.usecases.GetCharactersUseCase
import com.example.feature_characters.pagingsourse.PagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val useCase: GetCharactersUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    val list: Flow<PagingData<SimpleCharacter>> = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { PagingSource(useCase) }
    ).flow.cachedIn(viewModelScope)

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
class CharactersViewModelFactory : ViewModelProvider.Factory {
    private val repository = RepoImpl(apollo)
    private val useCase = GetCharactersUseCase(repository)
    private val getCharacterUseCase = GetCharacterUseCase(repository)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(useCase, getCharacterUseCase) as T
    }
}