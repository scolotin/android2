package com.example.android2.repository

import com.example.android2.model.ActorDTO
import com.example.android2.model.ActorDetailsDTO
import com.example.android2.model.FilmDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmAPI {
    @GET("3/movie/popular")
    fun getPopularFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<FilmDTO>

    @GET("3/person/popular")
    fun getPopularActors(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<ActorDTO>

    @GET("3/person/{person_id}")
    fun getActorDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Call<ActorDetailsDTO>
}