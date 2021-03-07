package com.example.android2.viewmodel

import com.example.android2.model.Actor
import com.example.android2.model.ActorDetails
import com.example.android2.model.Film

data class MainFragmentVMContainer(
    val filmList: ArrayList<Film>? = null,
    val actorList: List<Actor>? = null,
    val actorDetails: ActorDetails? = null
)
