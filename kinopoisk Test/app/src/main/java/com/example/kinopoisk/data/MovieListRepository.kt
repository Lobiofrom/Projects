package com.example.kinopoisk.data

import com.example.kinopoisk.entity.Item
import com.example.kinopoisk.entity.Movie
import com.example.kinopoisk.entity.StaffItem

class MovieListRepository {
    private val retrofitAndApi = RetrofitAndApi()
    suspend fun getPremiers(month: String, year: String, page: Int?): List<Movie> {
        return retrofitAndApi.api.getPremiers(month, year, page).items
    }

    suspend fun getTop250(page: Int?): List<Movie> {
        return retrofitAndApi.api.getTop250(page).films
    }

    suspend fun getPopular(page: Int?): List<Movie> {
        return retrofitAndApi.api.getPopular(page).films
    }
    suspend fun getSelection(countries: Int, genres: Int, type: String, yearFrom: Int, page: Int?): List<Movie> {
        return retrofitAndApi.api.getSelection(countries, genres, type, yearFrom, page).items
    }

    suspend fun getMovieDescription(id: Int) : Movie {
        return retrofitAndApi.api.getMovieDescription(id)
    }

    suspend fun getStaff(filmId: Int): ArrayList<StaffItem> {
        return retrofitAndApi.api.getStaff(filmId)
    }

    suspend fun getPictures(id: Int, page: Int?, type: String): List<Item> {
        return retrofitAndApi.api.getPictures(id, page, type).items
    }

    suspend fun getSimilars(id: Int): List<Movie> {
        return retrofitAndApi.api.getSimilars(id).items
    }

    suspend fun getPerson(id: Int): PersonDto {
        return retrofitAndApi.api.getPersonDetails(id)
    }
}