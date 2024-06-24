package com.mohassan.homecompass.auth_feature.data.repository

import com.mohassan.homecompass.auth_feature.data.remote.ApiService
import com.mohassan.homecompass.auth_feature.data.remote.ConfirmEmailRequest
import com.mohassan.homecompass.auth_feature.data.remote.dto.LoginRequestBody
import com.mohassan.homecompass.auth_feature.data.remote.dto.RegisterRequestBody
import com.mohassan.homecompass.auth_feature.domain.repository.UserRepository
import com.mohassan.homecompass.core.utils.Resource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiService: ApiService) : UserRepository {
    override suspend fun registerUser(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String
    ): Resource<Unit> {
        return try {
            val response = apiService.registerUser(
                RegisterRequestBody(
                    firstName,
                    lastName,
                    username,
                    email,
                    password
                )
            )
            if (response.isSuccessful) {
                Resource.Success(Unit)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error("Network error: ${e.message}")
        }
    }

    override suspend fun confirmEmail(
        email: String,
        token: String
    ): Resource<Unit> {
        return try {
            val response = apiService.confirmEmail(
                ConfirmEmailRequest(email, token)
            )
            if (response.isSuccessful) {
                Resource.Success(Unit)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error("Network error: ${e.message}")
        }
    }

    override suspend fun loginUser(email: String, password: String): Resource<Unit> {
        return try {
            val response = apiService.loginUser(LoginRequestBody(email, password))
            if (response.isSuccessful) {
                Resource.Success(Unit)
            } else {
                Resource.Error("Login failed")
            }
        } catch (e: Exception) {
            Resource.Error("Network error: ${e.message}")
        }
    }
}