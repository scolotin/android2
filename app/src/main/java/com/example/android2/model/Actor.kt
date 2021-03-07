package com.example.android2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actor(
    val id: Int,
    val name: String
) : Parcelable

data class ActorDTO(
    val results: List<Actor>
)

@Parcelize
data class ActorDetails(
    val name: String,
    val place_of_birth: String
) : Parcelable

data class ActorDetailsDTO(
    val name: String,
    val place_of_birth: String
)