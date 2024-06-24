package com.mohassan.homecompass.auth_feature.domain.repository

import com.mohassan.homecompass.core.utils.Resource
import retrofit2.Response

interface UserRepository {
    suspend fun registerUser(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String
    ): Resource<Unit>

    suspend fun confirmEmail(email: String, token: String): Response<Any>


    suspend fun loginUser(email: String, password: String): Resource<Unit>
}