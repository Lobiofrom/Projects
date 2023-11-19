package com.example.hotels.di

import com.example.data.repositoryImpl.RepositoryImpl
import com.example.data.retrofit.RetrofitAndApi
import com.example.domain.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(retrofitAndApi: RetrofitAndApi): Repository {
        return RepositoryImpl(retrofitAndApi)
    }
}