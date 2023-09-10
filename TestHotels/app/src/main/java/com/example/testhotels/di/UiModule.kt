package com.example.testhotels.di

import com.example.testhotels.domain.UseCase
import com.example.testhotels.ui.viewmodel.MyViewModel
import com.example.testhotels.ui.viewmodel.MyViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class UiModule {

    @Provides
    fun provideViewmodel(useCase: UseCase): MyViewModel {
        return MyViewModel(useCase)
    }

    @Provides
    fun provideFactory(myViewModel: MyViewModel): MyViewModelFactory {
        return MyViewModelFactory(myViewModel)
    }
}