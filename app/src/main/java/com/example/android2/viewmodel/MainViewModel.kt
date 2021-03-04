package com.example.android2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android2.app.App.Companion.getHistoryDAO
import com.example.android2.model.Film
import com.example.android2.model.FilmDTO
import com.example.android2.repository.*
import com.example.android2.utils.convertDtoToModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository = RepositoryImpl(RemoteDataSource()),
                    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDAO())) : ViewModel() {

    val filmListLiveData: MutableLiveData<MainVMContainer> = MutableLiveData()

    fun getFilmList() = filmListLiveData.apply {
        repository.getFilmListFromServer(callback)
    }

    fun saveFilmToDB(film: Film) {
        historyRepository.saveEntity(film)
    }

    private val callback = object : Callback<FilmDTO> {

        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            val serverResponse: FilmDTO? = response.body()
            filmListLiveData.postValue(MainVMContainer(convertDtoToModel(serverResponse)))
        }

        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {
            throw t
        }
    }
}