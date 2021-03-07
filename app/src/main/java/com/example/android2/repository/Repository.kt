package com.example.android2.repository

import com.example.android2.model.ActorDTO
import com.example.android2.model.ActorDetailsDTO
import com.example.android2.model.FilmDTO

interface Repository {
    fun getFilmListFromServer(callback: retrofit2.Callback<FilmDTO>)

    fun getActorListFromServer(callback: retrofit2.Callback<ActorDTO>)

    fun getActorDetailsFromServer(personId: Int, callback: retrofit2.Callback<ActorDetailsDTO>)
}
