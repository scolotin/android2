package com.example.android2.model

data class Film(
    val name: String,
    val rate: Float,
    val year: Int
)

fun getFilmList(): List<Film> {
    return listOf(
        Film("Tenet", 7.6F, 2020),
        Film("Escape from Pretoria", 6.7F, 2020),
        Film("Justice League Dark: Apokolips War", 7.1F, 2020)
    )
}
