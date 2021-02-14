package com.example.android2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android2.model.Repository
import com.example.android2.model.RepositoryImpl

class MainFragmentViewModel(private val repository: Repository = RepositoryImpl()) : ViewModel() {
    private val filmListLiveData: MutableLiveData<MainFragmentVMContainer> = MutableLiveData()

    fun getFilmListLiveData() = filmListLiveData

    fun getFilmList() = filmListLiveData.apply {
        postValue(MainFragmentVMContainer(repository.getFilmListFromServer()))
    }
}