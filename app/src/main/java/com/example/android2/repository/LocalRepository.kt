package com.example.android2.repository

import com.example.android2.model.Film

interface LocalRepository {
    fun getAllHistory(): ArrayList<Film>
    fun saveEntity(film: Film)
    fun getNote(film: Film): String?
}