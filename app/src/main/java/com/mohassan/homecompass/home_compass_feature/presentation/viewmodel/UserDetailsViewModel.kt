package com.mohassan.homecompass.home_compass_feature.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mohassan.homecompass.core.utils.Constants.ADDRESS
import com.mohassan.homecompass.core.utils.Constants.BIRTHDATE
import com.mohassan.homecompass.core.utils.Constants.EMAIL
import com.mohassan.homecompass.core.utils.Constants.FIRST_NAME
import com.mohassan.homecompass.core.utils.Constants.GENDER
import com.mohassan.homecompass.core.utils.Constants.LAST_NAME
import com.mohassan.homecompass.core.utils.Constants.PHONE
import com.mohassan.homecompass.core.utils.Constants.PHOTO_URI
import com.mohassan.homecompass.core.utils.SharedPrefManager
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.UserDetails

class UserDetailsViewModel(application: Application) : AndroidViewModel(application) {

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

    /*
    fun getUserData(): UserDetails {
        // Retrieve user data from SharedPreferencesManager
        return UserDetails(
            firstName = SharedPrefManager.getString(FIRST_NAME, "My").toString(),
            lastName = SharedPrefManager.getString(LAST_NAME, "Name").toString(),
            email = SharedPrefManager.getString(EMAIL, "email@gmail.com").toString(),
            gender = SharedPrefManager.getString(GENDER, "male").toString(),
            birthDate = SharedPrefManager.getString(BIRTHDATE, "7/11/2001").toString(),
            photoURL = SharedPrefManager.getString(PHOTO_URI, "").toString(),
            address = SharedPrefManager.getString(ADDRESS, "address").toString(),
            phone = SharedPrefManager.getString(PHONE, "0107101171").toString()
        )
    }
    */
}






