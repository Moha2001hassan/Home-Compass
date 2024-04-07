package com.mohassan.homecompass.home_compass_feature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.home_compass_feature.data.remote.ApiService
import com.mohassan.homecompass.home_compass_feature.data.remote.RetrofitInstance
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.UserDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserDetailsViewModel() : ViewModel() {

    private val _userDetailsLiveData: MutableLiveData<UserDetails> = MutableLiveData()
    val userDetailsLiveData: LiveData<UserDetails>
        get() = _userDetailsLiveData

    init {
        fetchUserDetails()
    }

    fun fetchUserDetails() {
        //get userId from shared pref saved when login
        val userId = "a9c56a87-6c1b-45c3-831b-53ef4713111d"

        try {
            viewModelScope.launch {
                val userDetails = RetrofitInstance.api.getUserDetails(userId)
                _userDetailsLiveData.postValue(userDetails)
            }
        } catch (e: Exception) {
            Log.e("TestAccountFragment", e.message.toString())
        }
    }

}