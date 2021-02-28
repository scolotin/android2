package com.example.android2.repository

import com.example.android2.model.API_KEY
import com.example.android2.model.FilmDTO
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    private val filmApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build().create(FilmAPI::class.java)

    fun getFilms(callback: Callback<FilmDTO>) {
        filmApi.getPopularFilms(API_KEY).enqueue(callback)
    }
}