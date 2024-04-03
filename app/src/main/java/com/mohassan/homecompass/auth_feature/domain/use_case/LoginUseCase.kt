package com.mohassan.homecompass.auth_feature.domain.use_case

import com.mohassan.homecompass.auth_feature.domain.repository.UserRepository
import com.mohassan.homecompass.core.utils.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(email: String, password: String): Resource<Unit> =
        userRepository.loginUser(email, password)
}