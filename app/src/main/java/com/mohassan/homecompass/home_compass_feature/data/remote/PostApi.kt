package com.mohassan.homecompass.home_compass_feature.data.remote

import com.mohassan.homecompass.home_compass_feature.data.remote.dto.Post
import retrofit2.http.GET

interface PostApi {

    @GET("Post")
    suspend fun getPosts(): List<Post>

//    @GET("posts/{id}")
//    suspend fun getPost(@Path("id") postId: Int): Post
}