package com.mohassan.homecompass.auth_feature.domain.repository

import com.mohassan.homecompass.core.utils.Resource

interface UserRepository {
    suspend fun registerUser(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String
    ): Resource<Unit>

    suspend fun loginUser(email: String, password: String): Resource<Unit>
}