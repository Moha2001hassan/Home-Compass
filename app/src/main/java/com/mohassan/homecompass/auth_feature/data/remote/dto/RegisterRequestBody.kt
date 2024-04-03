package com.mohassan.homecompass.auth_feature.data.remote.dto

data class RegisterRequestBody(
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val password: String
)