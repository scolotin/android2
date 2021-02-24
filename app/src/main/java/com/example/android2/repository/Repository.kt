package com.example.android2.repository

import com.example.android2.model.FilmDTO

interface Repository {
    fun getFilmListFromServer(callback: retrofit2.Callback<FilmDTO>)
}
