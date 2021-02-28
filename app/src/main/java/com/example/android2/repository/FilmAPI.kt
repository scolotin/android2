package com.example.android2.repository

import com.example.android2.model.FilmDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmAPI {
    @GET("3/movie/popular")
    fun getPopularFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<FilmDTO>
}