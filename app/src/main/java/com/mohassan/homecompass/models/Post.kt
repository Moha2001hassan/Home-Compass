package com.mohassan.homecompass.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Post(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
): Serializable