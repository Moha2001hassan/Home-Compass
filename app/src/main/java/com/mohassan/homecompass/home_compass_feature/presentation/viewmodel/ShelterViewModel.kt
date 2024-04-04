package com.mohassan.homecompass.home_compass_feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.home_compass_feature.data.remote.RetrofitInstance
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.FacilitiesBody
import kotlinx.coroutines.launch

class ShelterViewModel: ViewModel() {
    private val _shelters: MutableLiveData<List<FacilitiesBody>> = MutableLiveData()
    val shelters: LiveData<List<FacilitiesBody>>
        get() = _shelters

    init {
        getShelters()
    }

    fun getShelters() {
        viewModelScope.launch {
            val fetchedShelters = RetrofitInstance.api.getShelter()
            _shelters.value = fetchedShelters
        }
    }
}