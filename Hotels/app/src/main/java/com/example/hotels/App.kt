package com.example.hotels

import android.app.Application
import com.example.hotels.di.DaggerAppComponent

class App : Application() {
    val appComponent1 = DaggerAppComponent.create().hotelViewModelFactory()
    val appComponent2 = DaggerAppComponent.create().roomsViewModelFactory()
    val appComponent3 = DaggerAppComponent.create().bookingViewModelFactory()

}
