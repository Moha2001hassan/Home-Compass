package com.mohassan.homecompass.home_compass_feature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.home_compass_feature.data.remote.RetrofitInstance
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.FacilitiesBody
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RestaurantViewModel : ViewModel() {
    private val _restaurants: MutableLiveData<List<FacilitiesBody>> = MutableLiveData()
    val restaurants: LiveData<List<FacilitiesBody>>
        get() = _restaurants

    init {
        getRestaurants()
    }

    private fun getRestaurants() {
        viewModelScope.launch {
            try {
                Log.d("RestaurantViewModel", "Fetching restaurants...")
                val fetchedRestaurants = RetrofitInstance.api.getRestaurants()
                _restaurants.value = fetchedRestaurants
                Log.d("RestaurantViewModel", "Fetched ${fetchedRestaurants.size} restaurants")
            } catch (e: Exception) {
                _restaurants.value = emptyList()
                Log.e("RestaurantViewModel", "Error fetching restaurants: ${e.message}")
            } catch (e: HttpException) {
                _restaurants.value = emptyList()
                Log.e("RestaurantViewModel", "HTTP error fetching restaurants: ${e.message()}")
            } catch (e: IOException) {
                _restaurants.value = emptyList()
                Log.e("RestaurantViewModel", "Network error fetching restaurants: ${e.message}")
            }
        }
    }
}