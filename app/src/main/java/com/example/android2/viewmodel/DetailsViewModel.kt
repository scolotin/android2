package com.example.android2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android2.app.App.Companion.getHistoryDAO
import com.example.android2.model.Film
import com.example.android2.repository.LocalRepository
import com.example.android2.repository.LocalRepositoryImpl

class DetailsViewModel(private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDAO())) : ViewModel() {

    val detailsLiveData: MutableLiveData<MainVMContainer> = MutableLiveData()

    fun saveNote(film: Film) {
        historyRepository.saveEntity(film)
    }

    fun loadNote(film: Film): String? {
        return historyRepository.getNote(film)
    }
}