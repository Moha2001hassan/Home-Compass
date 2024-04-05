package com.mohassan.homecompass.home_compass_feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.home_compass_feature.data.remote.RetrofitInstance
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.FacilitiesBody
import kotlinx.coroutines.launch

class RestaurantViewModel: ViewModel() {
    private val _restaurants: MutableLiveData<List<FacilitiesBody>> = MutableLiveData()
    val restaurants: LiveData<List<FacilitiesBody>>
        get() = _restaurants

    init {
        getRestaurants()
    }

    fun getRestaurants() {
        viewModelScope.launch {
            val fetchedShelters = RetrofitInstance.api.getRestaurants()
            _restaurants.value = fetchedShelters
        }
    }
}