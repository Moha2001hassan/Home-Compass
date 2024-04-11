package com.mohassan.homecompass.home_compass_feature.presentation

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

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData to hold user details
    private val _userDetailsLiveData = MutableLiveData<UserDetails>()
    val userDetailsLiveData: LiveData<UserDetails>
        get() = _userDetailsLiveData


    fun loadUserDetails() {
        val userDetails = UserDetails(
            firstName = SharedPrefManager.getString(FIRST_NAME, "My").toString(),
            lastName = SharedPrefManager.getString(LAST_NAME, "Name").toString(),
            email = SharedPrefManager.getString(EMAIL, "email@gmail.com").toString(),
            gender = SharedPrefManager.getString(GENDER, "male").toString(),
            birthDate = SharedPrefManager.getString(BIRTHDATE, "7/11/2001").toString(),
            photoURL = SharedPrefManager.getString(PHOTO_URI, "").toString(),
            address = SharedPrefManager.getString(ADDRESS, "address").toString(),
            phone = SharedPrefManager.getString(PHONE, "0107101171").toString()
        )
        _userDetailsLiveData.postValue(userDetails)
    }
}
