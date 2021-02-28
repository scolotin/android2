package com.example.android2.model

interface Repository {
    fun getFilmListFromServer(): ArrayList<Film>
}
