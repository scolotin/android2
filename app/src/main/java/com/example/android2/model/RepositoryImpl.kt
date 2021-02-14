package com.example.android2.model

class RepositoryImpl : Repository {
    override fun getFilmListFromServer(): List<Film> = getFilmList()
}