package com.example.android2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android2.model.ActorDTO
import com.example.android2.model.ActorDetailsDTO
import com.example.android2.model.FilmDTO
import com.example.android2.repository.RemoteDataSource
import com.example.android2.repository.Repository
import com.example.android2.repository.RepositoryImpl
import com.example.android2.utils.convertActorDetailsDtoToModel
import com.example.android2.utils.convertActorDtoToModel
import com.example.android2.utils.convertFilmDtoToModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentViewModel(private val repository: Repository = RepositoryImpl(RemoteDataSource())) : ViewModel() {

    val filmListLiveData: MutableLiveData<MainFragmentVMContainer> = MutableLiveData()
    val actorListLiveData: MutableLiveData<MainFragmentVMContainer> = MutableLiveData()
    val actorDetailsLiveData: MutableLiveData<MainFragmentVMContainer> = MutableLiveData()

    fun getFilmList() = filmListLiveData.apply {
        repository.getFilmListFromServer(callbackFilmDTO)
    }

    fun getActorList() = actorListLiveData.apply {
        repository.getActorListFromServer(callbackActorDTO)
    }

    fun getActorDetails(personId: Int) = actorDetailsLiveData.apply {
        repository.getActorDetailsFromServer(personId, callbackActorDetailsDTO)
    }

    private val callbackFilmDTO = object : Callback<FilmDTO> {

        override fun onResponse(call: Call<FilmDTO>, response: Response<FilmDTO>) {
            val serverResponse: FilmDTO? = response.body()
            filmListLiveData.postValue(MainFragmentVMContainer(filmList = convertFilmDtoToModel(serverResponse)))
        }

        override fun onFailure(call: Call<FilmDTO>, t: Throwable) {

        }
    }

    private val callbackActorDTO = object : Callback<ActorDTO> {

        override fun onResponse(call: Call<ActorDTO>, response: Response<ActorDTO>) {
            val serverResponse: ActorDTO? = response.body()
            actorListLiveData.postValue(MainFragmentVMContainer(actorList = convertActorDtoToModel(serverResponse)))
        }

        override fun onFailure(call: Call<ActorDTO>, t: Throwable) {

        }
    }

    private val callbackActorDetailsDTO = object : Callback<ActorDetailsDTO> {

        override fun onResponse(call: Call<ActorDetailsDTO>, response: Response<ActorDetailsDTO>) {
            val serverResponse: ActorDetailsDTO? = response.body()
            actorDetailsLiveData.postValue(MainFragmentVMContainer(actorDetails = convertActorDetailsDtoToModel(serverResponse)))
        }

        override fun onFailure(call: Call<ActorDetailsDTO>, t: Throwable) {

        }
    }
}