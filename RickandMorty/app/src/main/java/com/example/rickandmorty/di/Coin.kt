package com.example.rickandmorty.di

import com.example.api.data.apollo
import com.example.feature_characters.data.RepoImpl
import com.example.feature_characters.domain.usecases.GetCharactersUseCase
import com.example.feature_characters.viewmodel.CharactersViewModel
import com.example.feature_locations.presentation.data.repositoryimpl.RepositoryImpl
import com.example.feature_locations.presentation.domain.usecase.GetLocationsUseCase
import com.example.feature_locations.presentation.viewmodel.LocationsViewModel
import com.example.feature_search.VM.FindCharacterVM
import com.example.feature_search.domain.findCharacterUseCase.FindCharacterUseCase
import com.example.feature_search.domain.repository.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<com.example.feature_characters.domain.repository.Repository> { RepoImpl(apollo) }
    single<com.example.feature_locations.presentation.domain.repository.Repository> {
        RepositoryImpl(
            apollo
        )
    }
    single<Repository> { com.example.feature_search.data.repoimpl.RepoImpl(apollo) }

    factory { GetCharactersUseCase(get()) }
    factory { GetLocationsUseCase(get()) }
    factory { FindCharacterUseCase(get()) }

    viewModel { LocationsViewModel(get()) }
    viewModel { CharactersViewModel(get()) }
    viewModel { FindCharacterVM(get()) }
}
