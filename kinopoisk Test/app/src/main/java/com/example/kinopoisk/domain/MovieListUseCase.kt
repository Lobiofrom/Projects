package com.example.kinopoisk.domain

import com.example.kinopoisk.data.MovieListRepository
import com.example.kinopoisk.data.PersonDto
import com.example.kinopoisk.entity.Item
import com.example.kinopoisk.entity.Movie
import com.example.kinopoisk.entity.Season
import com.example.kinopoisk.entity.StaffItem

class MovieListUseCase {
    private val movieListRepository = MovieListRepository()

    suspend fun executePremiers(month: String, year: String, page: Int?): List<Movie> {
        return movieListRepository.getPremiers(month, year, page)
    }

    suspend fun execute250(page: Int?): List<Movie> {
        return movieListRepository.getTop250(page)
    }

    suspend fun executePopular(page: Int?): List<Movie> {
        return movieListRepository.getPopular(page)
    }

    suspend fun executeSelection(
        countries: Int,
        genres: Int,
        type: String,
        yearFrom: Int,
        page: Int?
    ): List<Movie> {
        return movieListRepository.getSelection(countries, genres, type, yearFrom, page)
    }

    suspend fun executeMovieDescription(id: Int): Movie {
        return movieListRepository.getMovieDescription(id)
    }

    suspend fun executeStaff(filmId: Int): ArrayList<StaffItem> {
        return movieListRepository.getStaff(filmId)
    }

    suspend fun executePictures(id: Int, page: Int?, type: String): List<Item> {
        return movieListRepository.getPictures(id, page, type)
    }

    suspend fun executeSimilars(id: Int): List<Movie> {
        return movieListRepository.getSimilars(id)
    }

    suspend fun executePerson(id: Int): PersonDto {
        return movieListRepository.getPerson(id)
    }

    suspend fun executeSereies(id: Int): List<Season> {
        return movieListRepository.getSeries(id)
    }

    suspend fun executeSearch(
        countries: Int?,
        genres: Int?,
        order: String,
        type: String,
        yearFrom: Int,
        yearTo: Int,
        ratingFrom: Int,
        ratingTo: Int,
        keyword: String,
        page: Int?
    ): List<Movie> {
        return movieListRepository.search(
            countries,
            genres,
            order,
            type,
            yearFrom,
            yearTo,
            ratingFrom,
            ratingTo,
            keyword,
            page
        )
    }

    suspend fun executePersonSearch(name: String, page: Int): List<StaffItem> {
        return movieListRepository.searchPersons(name, page)
    }
}