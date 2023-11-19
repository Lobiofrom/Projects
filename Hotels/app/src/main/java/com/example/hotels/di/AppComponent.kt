package com.example.hotels.di

import com.example.hotels.viewModels.BookingViewModelFactory
import com.example.hotels.viewModels.HotelViewModelFactory
import com.example.hotels.viewModels.RoomsViewModelFactory
import dagger.Component

@Component(
    modules = [
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun hotelViewModelFactory(): HotelViewModelFactory
    fun roomsViewModelFactory(): RoomsViewModelFactory
    fun bookingViewModelFactory(): BookingViewModelFactory
}