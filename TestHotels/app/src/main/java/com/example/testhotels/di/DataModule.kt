package com.example.testhotels.di

import com.example.testhotels.data.Repository
import com.example.testhotels.data.RetrofitAndApi
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideRetrofit(): RetrofitAndApi {
        return RetrofitAndApi()
    }

    @Provides
    fun provideRepo(retrofitAndApi: RetrofitAndApi): Repository {
        return Repository(retrofitAndApi)
    }
}