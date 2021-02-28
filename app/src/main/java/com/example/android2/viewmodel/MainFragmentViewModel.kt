package com.example.android2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android2.model.FilmDTO
import com.example.android2.repository.RemoteDataSource
import com.example.android2.repository.Repository
import com.example.android2.repository.RepositoryImpl
import com.example.android2.utils.convertDtoToModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentViewModel(private val repository: Repository = RepositoryImpl(RemoteDataSource())) : ViewModel() {

    val filmListLiveData: MutableLiveData<MainFragmentVMContainer> = MutableLiveData()

    fun getFilmList() = filmListLiveData.apply {
        repository.getFilmListFromServer(callback)
    }

    private val callback = object : Callback<FilmDTO> {

        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            val serverResponse: FilmDTO? = response.body()
            filmListLiveData.postValue(MainFragmentVMContainer(convertDtoToModel(serverResponse)))
        }

        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {

        }
    }
}