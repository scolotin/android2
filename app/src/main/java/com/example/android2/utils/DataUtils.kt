package com.example.android2.utils

import com.example.android2.model.*

fun convertFilmDtoToModel(filmDTO: FilmDTO?): ArrayList<Film> = filmDTO?.results!!

fun convertActorDtoToModel(actorDTO: ActorDTO?): List<Actor> = actorDTO?.results!!

fun convertActorDetailsDtoToModel(actorDetailsDTO: ActorDetailsDTO?) : ActorDetails {
    return actorDetailsDTO?.run {
        ActorDetails(this.name, this.place_of_birth)
    }!!
}