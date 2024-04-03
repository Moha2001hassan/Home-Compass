package com.mohassan.homecompass.auth_feature.domain.use_case

import com.mohassan.homecompass.auth_feature.domain.repository.UserRepository
import com.mohassan.homecompass.core.utils.Resource
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String
    ): Resource<Unit> =
        userRepository.registerUser(firstName, lastName, username, email, password)
}