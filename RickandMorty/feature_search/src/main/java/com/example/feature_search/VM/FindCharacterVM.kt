package com.example.feature_search.VM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.feature_search.domain.findCharacterUseCase.FindCharacterUseCase
import com.example.feature_search.domain.models.FoundCharacter
import com.example.feature_search.pagingSource.PagingSourceCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FindCharacterVM(
    private val useCase: FindCharacterUseCase
) : ViewModel() {

    private var _list = MutableStateFlow<PagingData<FoundCharacter>>(PagingData.empty())
    val list = _list.asStateFlow()
    fun findCharacter(name: String, status: String) {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(20),
                pagingSourceFactory = {
                    PagingSourceCharacter(
                        useCase = useCase,
                        name = name,
                        status = status
                    )
                }
            ).flow.cachedIn(viewModelScope)
                .collect {
                    _list.value = it
                }
        }
    }
}