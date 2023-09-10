package com.example.testhotels.di

import com.example.testhotels.ui.viewmodel.MyViewModelFactory
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        UiModule::class
    ]
)
interface AppComponent {
    fun myViewModelFactory(): MyViewModelFactory
}
