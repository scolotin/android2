package com.example.android2.repository

import com.example.android2.model.*
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val filmAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(FilmAPI::class.java)

    fun getFilms(callback: Callback<FilmDTO>) {
        filmAPI.getPopularFilms(API_KEY).enqueue(callback)
    }

    fun getActors(callback: Callback<ActorDTO>) {
        filmAPI.getPopularActors(API_KEY).enqueue(callback)
    }

    fun getActorDetails(personId: Int, callback: Callback<ActorDetailsDTO>) {
        filmAPI.getActorDetails(personId, API_KEY).enqueue(callback)
    }
}