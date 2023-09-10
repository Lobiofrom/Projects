package com.example.testhotels.di

import com.example.testhotels.data.Repository
import com.example.testhotels.domain.UseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun privideUsecase(repository: Repository): UseCase {
        return UseCase(repository)
    }
}