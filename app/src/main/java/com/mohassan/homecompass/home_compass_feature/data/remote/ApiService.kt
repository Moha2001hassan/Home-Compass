package com.mohassan.homecompass.home_compass_feature.data.remote

import com.mohassan.homecompass.home_compass_feature.data.remote.dto.FacilitiesBody
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.JobBody
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.Post
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.PostRequest
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.UserDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {
    @GET("Post")
    suspend fun getPosts(): List<Post>
    @POST("Post")
    suspend fun publishPost(@Body postRequest: PostRequest): Response<Void>

    @GET("Job")
    suspend fun getJobs(): List<JobBody>

    @GET("Facility/bycategory/7")
    suspend fun getShelter(): List<FacilitiesBody>

    @GET("Facility/bycategory/10")
    suspend fun getRestaurants(): List<FacilitiesBody>

    @GET("User/details")
    suspend fun getUserDetails(@Query("id") id: String): UserDetails

    @PUT("User/details")
    suspend fun updateUserDetails(@Query("id") id: String, @Body userDetails: UserDetails): Response<Void>


}
