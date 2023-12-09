package com.example.feature_locations.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.api.data.apollo
import com.example.feature_locations.presentation.data.repositoryimpl.RepositoryImpl
import com.example.feature_locations.presentation.domain.model.Location
import com.example.feature_locations.presentation.domain.usecase.GetLocationsUseCase
import com.example.feature_locations.presentation.pagingsource.PagingSource
import kotlinx.coroutines.flow.Flow

class LocationsViewModel(
    private val useCase: GetLocationsUseCase
) : ViewModel() {
    val locations: Flow<PagingData<Location>> = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { PagingSource(useCase) }
    ).flow.cachedIn(viewModelScope)
}
//
//@Suppress("UNCHECKED_CAST")
//class LocationsViewModelFactory : ViewModelProvider.Factory {
//    private val useCase = GetLocationsUseCase(RepositoryImpl(apollo))
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return LocationsViewModel(useCase) as T
//    }
//}