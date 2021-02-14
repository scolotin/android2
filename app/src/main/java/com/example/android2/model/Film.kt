package com.example.android2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val name: String,
    val year: Int,
    val genre: String,
    val rate: Float,
    val description: String
) : Parcelable

fun getFilmList(): List<Film> {
    return listOf(
        Film("Tenet", 2020, " Action, Sci-Fi, Thriller", 7.6F, "In a twilight world of international espionage, an unnamed CIA operative, known as The Protagonist, is recruited by a mysterious organization called Tenet to participate in a global assignment that unfolds beyond real time. The mission: prevent Andrei Sator, a renegade Russian oligarch with precognition abilities, from starting World War III. The Protagonist will soon master the art of \"time inversion\" as a way of countering the threat that is to come."),
        Film("Escape from Pretoria", 2020, "Thriller", 6.7F, "Based on the real-life prison break of two political captives, Escape From Pretoria is a race-against-time thriller set in the tumultuous apartheid days of South Africa."),
        Film("Justice League Dark: Apokolips War", 2020, " Animation, Action, Sci-Fi", 7.1F, "A poorly executed attack on Apokolips results in the deaths of many of DC's heroes and Darkseid successfully conquering the earth. Now the remaining heroes must regroup and consider a new strategy in order to take their planet back from the intergalactic tyrant.")
    )
}