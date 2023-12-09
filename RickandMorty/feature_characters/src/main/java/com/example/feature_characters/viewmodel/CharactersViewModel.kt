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
import com.example.feature_characters.domain.model.SimpleCharacter
import com.example.feature_characters.domain.usecases.GetCharactersUseCase
import com.example.feature_characters.pagingsourse.PagingSource
import kotlinx.coroutines.flow.Flow

class CharactersViewModel(
    private val useCase: GetCharactersUseCase,
) : ViewModel() {

    val list: Flow<PagingData<SimpleCharacter>> = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { PagingSource(useCase) }
    ).flow.cachedIn(viewModelScope)
}
//
//@Suppress("UNCHECKED_CAST")
//class CharactersViewModelFactory : ViewModelProvider.Factory {
//    private val repository = RepoImpl(apollo)
//    private val useCase = GetCharactersUseCase(repository)
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return CharactersViewModel(useCase) as T
//    }
//}