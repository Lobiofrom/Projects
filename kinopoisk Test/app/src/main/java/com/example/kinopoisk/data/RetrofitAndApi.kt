package com.example.kinopoisk.data

import com.example.kinopoisk.entity.Movie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "e117253f-1c92-459e-b8f3-6b5c08c5f17f"
    //"22fe150a-c4b8-43b9-bc23-f52a5d11ab0d"
//"94faef07-7bdf-486d-ba34-5669bc1f983a"
//"765266b1-15e5-4fc0-a9bd-d29696b30029"
//"03e4ab10-b9e4-4bd6-b6d6-0d7321495bfa"

class RetrofitAndApi {

    val api: PremiersApi by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                }).build()
            )
            .build()
            .create(PremiersApi::class.java)
    }

    interface PremiersApi {

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v2.2/films/premieres?")
        suspend fun getPremiers(
            @Query("month") month: String,
            @Query("year") year: String,
            @Query("page") page: Int?
        ): MovieListDto

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v2.2/films/top?type=TOP_250_BEST_FILMS")
        suspend fun getTop250(
            @Query("page") page: Int?
        ): PagedMoviesDto

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v2.2/films/top?type=TOP_100_POPULAR_FILMS")
        suspend fun getPopular(
            @Query("page") page: Int?
        ): PagedMoviesDto

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v2.2/films?order=RATING&ratingFrom=8&ratingTo=10&yearTo=3000")
        suspend fun getSelection(
            @Query("countries") countries: Int,
            @Query("genres") genres: Int,
            @Query("type") type: String,
            @Query("yearFrom") yearFrom: Int,
            @Query("page") page: Int?
        ): MovieSelectionDto

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v2.2/films/{id}")
        suspend fun getMovieDescription(
            @Path("id") id: Int
        ): Movie

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v2.2/films/{id}/images")
        suspend fun getPictures(
            @Path("id") id: Int,
            @Query("page") page: Int?,
            @Query("type") type: String
        ): PicturesDto

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v2.2/films/{id}/similars")
        suspend fun getSimilars(
            @Path("id") id: Int,
        ): MovieListDto

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v1/staff?")
        suspend fun getStaff(
            @Query("filmId") filmId: Int
        ): StaffDto

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v1/staff/{id}")
        suspend fun getPersonDetails(
            @Path("id") id: Int?
        ): PersonDto

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v2.2/films/{id}/seasons")
        suspend fun getSeries(
            @Path("id") id: Int
        ): SeriesDto

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v2.2/films?order=RATING")
        suspend fun search(
            @Query("type") type: String,
            @Query("yearFrom") yearFrom: Int,
            @Query("yearTo") yearTo: Int,
            @Query("ratingFrom") ratingFrom: Int,
            @Query("ratingTo") ratingTo: Int,
            @Query("keyword") keyword: String,
            @Query("page") page: Int?
        ): MovieSelectionDto

        @Headers("X-API-KEY: $API_KEY")
        @GET("/api/v1/persons?")
        suspend fun searchPersons(
            @Query("name") name: String,
            @Query("page") page: Int
        ): SearchPersonListDto
    }
}