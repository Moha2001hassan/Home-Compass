package com.mohassan.homecompass.home_compass_feature.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.UserDetails

class UserDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences: SharedPreferences by lazy {
        application.getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
    }

    private val _userDetailsLiveData: MutableLiveData<UserDetails> = MutableLiveData()
    val userDetailsLiveData: LiveData<UserDetails>
        get() = _userDetailsLiveData

    private val _selectedDateLiveData = MutableLiveData<String>()
    val selectedDateLiveData: LiveData<String>
        get() = _selectedDateLiveData

    fun updateSelectedDate(selectedDate: String) {
        _selectedDateLiveData.value = selectedDate
    }

    fun updateUserData(
        firstName: String,
        lastName: String,
        email: String,
        gender: String,
        birthDate: String,
        photoURL: String,
        address: String,
        phone: String,
    ) {
        // Save user data to SharedPreferences
        with(sharedPreferences.edit()) {
            putString("firstName", firstName)
            putString("lastName", lastName)
            putString("email", email)
            putString("gender", gender)
            putString("birthDate", birthDate)
            putString("photoURL", photoURL)
            putString("address", address)
            putString("phone", phone)
            apply()
        }
    }

    fun getUserData(): UserDetails {
        return UserDetails(
            firstName = sharedPreferences.getString("firstName", "") ?: "My",
            lastName = sharedPreferences.getString("lastName", "") ?: "Name",
            email = sharedPreferences.getString("email", "") ?: "email@gmail.com",
            gender = sharedPreferences.getString("gender", "") ?: "male",
            birthDate = sharedPreferences.getString("birthDate", "") ?: "7/11/2001",
            photoURL = sharedPreferences.getString("photoURL", "") ?: "",
            address = sharedPreferences.getString("address", "") ?: "address",
            phone = sharedPreferences.getString("phone", "") ?: "0107101171"
        )
    }
}






