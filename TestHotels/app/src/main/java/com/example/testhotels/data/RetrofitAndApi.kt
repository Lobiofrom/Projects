package com.example.testhotels.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitAndApi {

    val api: HotelApi by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }).build()
            )
            .build()
            .create(HotelApi::class.java)
    }

    interface HotelApi {
        @GET("/v3/35e0d18e-2521-4f1b-a575-f0fe366f66e3")
        suspend fun getHotel(): HotelDto
    }
}