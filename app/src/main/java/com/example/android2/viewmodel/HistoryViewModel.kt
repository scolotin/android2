package com.example.android2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android2.app.App.Companion.getHistoryDAO
import com.example.android2.repository.LocalRepository
import com.example.android2.repository.LocalRepositoryImpl

class HistoryViewModel(private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDAO())) : ViewModel() {

    val historyLiveData: MutableLiveData<MainVMContainer> = MutableLiveData()

    fun getAllHistory() {
        historyLiveData.value = MainVMContainer(historyRepository.getAllHistory())
    }
}