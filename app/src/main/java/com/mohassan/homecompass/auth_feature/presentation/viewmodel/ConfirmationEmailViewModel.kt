package com.mohassan.homecompass.auth_feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.auth_feature.domain.repository.UserRepository
import com.mohassan.homecompass.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ConfirmationEmailViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _confirmEmailState = MutableSharedFlow<Resource<Any>>()
    val confirmEmailState = _confirmEmailState

    fun confirmEmail(email: String, token: String) {
        viewModelScope.launch {
            try {
                _confirmEmailState.emit ( Resource.Loading())
                val response = userRepository.confirmEmail(email, token)
                _confirmEmailState.emit( Resource.Success(response))
            } catch (e: Exception) {
                _confirmEmailState.emit( Resource.Error(e.message ?: "Unknown error"))
            }
        }
    }
}