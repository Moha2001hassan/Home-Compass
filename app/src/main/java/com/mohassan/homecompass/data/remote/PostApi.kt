package com.mohassan.homecompass.data.remote

import com.mohassan.homecompass.models.Post
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): List<Post>

//    @GET("posts/{id}")
//    suspend fun getPost(@Path("id") postId: Int): Post
}