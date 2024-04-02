package com.mohassan.homecompass.home_compass_feature.data.remote.dto

data class PostRequest(
    val title: String,
    val content: String,
    val publishedOn: String,
    val userId: String
)
