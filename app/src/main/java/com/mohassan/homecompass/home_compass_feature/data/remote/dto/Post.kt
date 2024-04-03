package com.mohassan.homecompass.home_compass_feature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("authorName")
    val authorName: String,
    @SerializedName("authorPhotoUrl")
    val authorPhotoUrl: String,
    @SerializedName("commentsCount")
    val commentsCount: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("likesCount")
    val likesCount: Int,
    @SerializedName("title")
    val title: String
)