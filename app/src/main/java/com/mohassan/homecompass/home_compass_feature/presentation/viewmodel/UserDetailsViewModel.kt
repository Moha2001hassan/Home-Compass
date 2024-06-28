package com.mohassan.homecompass.home_compass_feature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.auth_feature.data.datastore.UserPreferences
import com.mohassan.homecompass.auth_feature.data.remote.dto.RegisterRequestBody
import com.mohassan.homecompass.core.utils.Constants.ADDRESS
import com.mohassan.homecompass.core.utils.Constants.BIRTHDATE
import com.mohassan.homecompass.core.utils.Constants.EMAIL
import com.mohassan.homecompass.core.utils.Constants.FIRST_NAME
import com.mohassan.homecompass.core.utils.Constants.GENDER
import com.mohassan.homecompass.core.utils.Constants.LAST_NAME
import com.mohassan.homecompass.core.utils.Constants.PHONE
import com.mohassan.homecompass.core.utils.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _userData = MutableStateFlow<RegisterRequestBody?>(null)
    val userData: StateFlow<RegisterRequestBody?> = _userData

    private val _selectedDateLiveData = MutableLiveData<String>()
    val selectedDateLiveData: LiveData<String>
        get() = _selectedDateLiveData


    init {
        fetchUserDetails()
    }

    private fun fetchUserDetails() {
        viewModelScope.launch {
            _userData.value = userPreferences.getUserData()
        }
        Log.e("UserDetailsViewModel", "fetchUserDetails: ${userData.value}")
    }

    fun updateSelectedDate(selectedDate: String) {
        _selectedDateLiveData.value = selectedDate
    }

    fun updateUserData(
        firstName: String,
        lastName: String,
        email: String,
        gender: String,
        birthDate: String,
        address: String,
        phone: String,
    ) {
        // Save user data to SharedPreferencesManager
        SharedPrefManager.putString(FIRST_NAME, firstName)
        SharedPrefManager.putString(LAST_NAME, lastName)
        SharedPrefManager.putString(EMAIL, email)
        SharedPrefManager.putString(GENDER, gender)
        SharedPrefManager.putString(BIRTHDATE, birthDate)
        SharedPrefManager.putString(ADDRESS, address)
        SharedPrefManager.putString(PHONE, phone)
    }
}






