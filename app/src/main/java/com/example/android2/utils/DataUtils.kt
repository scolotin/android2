package com.example.android2.utils

import com.example.android2.model.Film
import com.example.android2.model.FilmDTO

fun convertDtoToModel(filmDTO: FilmDTO?): ArrayList<Film> {
    return filmDTO?.results!!
}