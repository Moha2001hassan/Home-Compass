package com.mohassan.homecompass.home_compass_feature.data.remote.dto


import com.google.gson.annotations.SerializedName

data class FacilitiesBody(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("contributorId")
    val useId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("contactInformaton")
    val phone: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("photoUrl")
    val photoUrl: String,
)