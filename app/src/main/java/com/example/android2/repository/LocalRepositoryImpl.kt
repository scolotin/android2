package com.example.android2.repository

import com.example.android2.model.Film
import com.example.android2.model.room.HistoryDAO
import com.example.android2.model.room.HistoryEntity

class LocalRepositoryImpl(private val localDataSource: HistoryDAO) : LocalRepository {

    override fun getAllHistory(): ArrayList<Film> {
        return convertHistoryEntityToFilm(localDataSource.all())
    }

    override fun saveEntity(film: Film) {
        localDataSource.insert(convertFilmToEntity(film))
    }

    override fun getNote(film: Film): String? {
        return getNoteString(localDataSource.getNote(film.title))
    }

    fun convertHistoryEntityToFilm(entityList: List<HistoryEntity>): ArrayList<Film> {
        return entityList.map {
            Film(it.title, "", it.release_date, "", 0.0F, "", it.note)
        } as ArrayList<Film>
    }

    fun convertFilmToEntity(film: Film): HistoryEntity {
        return HistoryEntity(0, film.title, film.release_date, film.note)
    }

    fun getNoteString(entityList: List<HistoryEntity>): String? {
        return when (entityList.size) {
            0 -> null
            else -> {
                val last = entityList.size - 1
                entityList[last].note
            }
        }
    }
}