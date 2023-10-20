package com.example.kinopoisk

import android.app.Application
import androidx.room.Room
import com.example.kinopoisk.data.AppDataBase
import com.google.firebase.crashlytics.FirebaseCrashlytics

class App : Application() {

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        ).fallbackToDestructiveMigration().build()
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }
}