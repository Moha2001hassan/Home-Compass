package com.mohassan.homecompass.home_compass_feature.data.remote

import com.mohassan.homecompass.home_compass_feature.data.remote.dto.Post
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.PostRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostApi {

    @GET("Post")
    suspend fun getPosts(): List<Post>

    @POST("Post")
    suspend fun publishPost(@Body postRequest: PostRequest): Response<Void>

//    @GET("posts/{id}")
//    suspend fun getPost(@Path("id") postId: Int): Post
}
