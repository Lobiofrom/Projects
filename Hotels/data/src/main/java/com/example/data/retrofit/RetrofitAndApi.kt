package com.example.data.retrofit

import com.example.data.dto.BookingDto
import com.example.data.dto.HotelDto
import com.example.data.dto.RoomsDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

class RetrofitAndApi @Inject constructor(){
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
        @GET("/v3/d144777c-a67f-4e35-867a-cacc3b827473")
        suspend fun getHotel(): HotelDto

        @GET("/v3/8b532701-709e-4194-a41c-1a903af00195")
        suspend fun getRooms(): RoomsDto

        @GET("/v3/63866c74-d593-432c-af8e-f279d1a8d2ff")
        suspend fun getBooking(): BookingDto
    }
}