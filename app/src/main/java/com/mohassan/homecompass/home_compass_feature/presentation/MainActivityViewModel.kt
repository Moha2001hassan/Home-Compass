package com.mohassan.homecompass.home_compass_feature.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mohassan.homecompass.core.utils.Constants.PREF_EMAIL_KEY
import com.mohassan.homecompass.core.utils.Constants.PREF_NAME_KEY
import com.mohassan.homecompass.core.utils.Constants.SHARED_PREF

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    init {
        // Load user data from SharedPreferences
        val sharedPref = application.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        _userName.value = sharedPref.getString(PREF_NAME_KEY, "null")
        _email.value = sharedPref.getString(PREF_EMAIL_KEY, "null")
    }
}
