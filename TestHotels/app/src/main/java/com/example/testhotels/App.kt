package com.example.testhotels

import android.app.Application
import com.example.testhotels.di.DaggerAppComponent

class App : Application() {
    val appComponent = DaggerAppComponent.create().myViewModelFactory()
}