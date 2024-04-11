package com.mohassan.homecompass.home_compass_feature.data.remote.dto


import com.google.gson.annotations.SerializedName

data class JobBody(
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("contactInformation")
    val contactInformation: String,
    @SerializedName("contributorId")
    val contributorId: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("skills")
    val skills: List<String>,
    @SerializedName("title")
    val title: String,

    @SerializedName("salary")
    val salary: String,
    @SerializedName("hours")
    val hours: String,
)