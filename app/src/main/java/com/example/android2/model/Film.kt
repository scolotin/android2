package com.example.android2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val title: String,
    val poster_path: String,
    val release_date: String,
    val genre: String,
    val vote_average: Float,
    val overview: String,
    var note: String?
) : Parcelable

data class FilmDTO(
    val results: ArrayList<Film>?
)