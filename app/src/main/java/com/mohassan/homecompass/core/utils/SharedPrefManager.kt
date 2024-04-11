package com.mohassan.homecompass.core.utils

import android.content.Context
import android.content.SharedPreferences
import com.mohassan.homecompass.core.utils.Constants.PREF_NAME

object SharedPrefManager {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }
}
