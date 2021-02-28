package com.example.android2.repository

import com.example.android2.model.FilmDTO

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override fun getFilmListFromServer(callback: retrofit2.Callback<FilmDTO>) {
        remoteDataSource.getFilms(callback)
    }
}