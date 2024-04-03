package com.mohassan.homecompass.home_compass_feature.data.remote

import com.mohassan.homecompass.home_compass_feature.data.remote.dto.JobBody
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.Post
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.PostRequest
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.ShelterBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("Post")
    suspend fun getPosts(): List<Post>
    @POST("Post")
    suspend fun publishPost(@Body postRequest: PostRequest): Response<Void>

    @GET("Homeless")
    suspend fun getShelter(): List<ShelterBody>

    @GET("Job")
    suspend fun getJobs(): List<JobBody>

}
