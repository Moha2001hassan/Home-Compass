package com.mohassan.homecompass.home_compass_feature.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.auth_feature.data.datastore.UserPreferences
import com.mohassan.homecompass.auth_feature.data.remote.dto.RegisterRequestBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _userDetails = MutableStateFlow<RegisterRequestBody?>(null)
    val userDetails: StateFlow<RegisterRequestBody?> = _userDetails

    init {
        fetchUserDetails()
    }

    private fun fetchUserDetails() {
        viewModelScope.launch {
            _userDetails.value = userPreferences.getUserData()
        }
        Log.e("MainActivityViewModel", "fetchUserDetails: ${userDetails.value}")
    }
}
