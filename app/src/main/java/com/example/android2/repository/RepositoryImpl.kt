package com.example.android2.repository

import com.example.android2.model.ActorDTO
import com.example.android2.model.ActorDetailsDTO
import com.example.android2.model.FilmDTO
import retrofit2.Callback

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {

    override fun getFilmListFromServer(callback: Callback<FilmDTO>) {
        remoteDataSource.getFilms(callback)
    }

    override fun getActorListFromServer(callback: Callback<ActorDTO>) {
        remoteDataSource.getActors(callback)
    }

    override fun getActorDetailsFromServer(personId: Int, callback: Callback<ActorDetailsDTO>) {
        remoteDataSource.getActorDetails(personId, callback)
    }
}