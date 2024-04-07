package com.mohassan.homecompass.home_compass_feature.data.remote.dto

data class UserDetails(
    val firstName: String,
    val lastName: String,
    val email: String,
    val gender: String,
    val birthDate: String,
    val photoURL: String,
    val address: String
)
