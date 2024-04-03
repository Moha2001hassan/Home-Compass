package com.mohassan.homecompass.home_compass_feature.data.remote.dto

import com.google.gson.annotations.SerializedName

//TODO Update is needed
data class ShelterBody(
    @SerializedName("address")
    val address: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("photoURL")
    val photoURL: String
)