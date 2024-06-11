package com.mohassan.homecompass.auth_feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.auth_feature.data.datastore.UserPreferences
import com.mohassan.homecompass.auth_feature.data.remote.dto.RegisterRequestBody
import com.mohassan.homecompass.auth_feature.domain.use_case.LoginUseCase
import com.mohassan.homecompass.auth_feature.domain.use_case.RegisterUseCase
import com.mohassan.homecompass.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
   private val userPreferences: UserPreferences
) : ViewModel() {

    private val _registrationState = MutableSharedFlow<Resource<Unit>>()
    val registrationState: SharedFlow<Resource<Unit>> = _registrationState

    private val _userData = MutableStateFlow<RegisterRequestBody?>(null)
    val userData: StateFlow<RegisterRequestBody?> = _userData

    private val _loginState = MutableSharedFlow<Resource<Unit>>()
    val loginState: SharedFlow<Resource<Unit>> = _loginState

    val isLoggedIn: LiveData<Boolean> = userPreferences.isLoggedIn.asLiveData()
    val isDonor: LiveData<Boolean> = userPreferences.isDonor.asLiveData()

    fun registerUser(
        firstName: String,
        lastName: String,
        username: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _registrationState.emit(Resource.Loading())
            val result = registerUseCase(firstName, lastName, username, email, password)
            if (result is Resource.Success) {
                userPreferences.saveUserData(
                    RegisterRequestBody(
                        firstName,
                        lastName,
                        username,
                        email,
                        password
                    )
                )
            }
            _registrationState.emit(result)
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _loginState.emit(Resource.Loading())
            val result = loginUseCase(email, password)
            if (result is Resource.Success) {
                userPreferences.setLoggedIn(true)
                val userData = userPreferences.getUserData()
                _userData.value = userData
            }
            _loginState.emit(result)
        }
    }
    fun logout() {
        viewModelScope.launch {
            userPreferences.setLoggedIn(false)
        }
    }
    suspend fun setIsDonor(isDonor: Boolean) {
        userPreferences.setIsDonor(isDonor)
    }
}