package com.mohassan.homecompass.home_compass_feature.data.remote

import com.mohassan.homecompass.core.utils.Constants.BASE_URL2
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val api: PostApi by lazy {
        retrofit.create(PostApi::class.java)
    }
}