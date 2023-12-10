package com.example.feature_search.VM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.feature_search.domain.findLocationUseCase.FindLocationUseCase
import com.example.feature_search.domain.models.FoundLocation
import com.example.feature_search.pagingSource.PagingSourceLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FindLocationVM(
    private val useCase: FindLocationUseCase
) : ViewModel() {

    private var _list = MutableStateFlow<PagingData<FoundLocation>>(PagingData.empty())
    val list = _list.asStateFlow()

    fun getLocation(name: String) {
        viewModelScope.launch {
            Pager(config = PagingConfig(20), pagingSourceFactory = {
                PagingSourceLocation(useCase, name)
            }).flow.cachedIn(viewModelScope).collect {
                _list.value = it
            }
        }
    }
}