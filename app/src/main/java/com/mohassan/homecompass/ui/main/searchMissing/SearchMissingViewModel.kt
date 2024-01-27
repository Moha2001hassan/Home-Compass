package com.mohassan.homecompass.ui.main.searchMissing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchMissingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is SearchMissing Fragment"
    }
    val text: LiveData<String> = _text
}