package com.example.testhotels.data

class Repository {
    private val retrofitAndApi = RetrofitAndApi()

    suspend fun getHotel(): HotelDto {
        return retrofitAndApi.api.getHotel()
    }
}