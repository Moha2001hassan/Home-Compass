package com.mohassan.homecompass.home_compass_feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.home_compass_feature.data.remote.RetrofitInstance
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.JobBody
import kotlinx.coroutines.launch

class JobViewModel: ViewModel() {
    private val _jobs: MutableLiveData<List<JobBody>> = MutableLiveData()
    val jobs: LiveData<List<JobBody>>
        get() = _jobs

    init {
        getJobs()
    }

    fun getJobs() {
        viewModelScope.launch {
            try {
                val fetchedJobs = RetrofitInstance.api.getJobs()
                _jobs.value = fetchedJobs
                println("fetch done successfully..")
            } catch (e:Exception){
                println("error: ---> ${e.message}")
            }

        }
    }
}