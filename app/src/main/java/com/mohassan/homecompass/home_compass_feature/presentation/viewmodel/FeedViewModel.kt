package com.mohassan.homecompass.home_compass_feature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.core.utils.Constants.USER_ID
import com.mohassan.homecompass.core.utils.GetCurrentDateTime.getCurrentDateTime
import com.mohassan.homecompass.home_compass_feature.data.remote.RetrofitInstance
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.Post
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.PostRequest
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>>
        get() = _posts

    init {
        getPosts()
    }

    fun getPosts() {
        try {
            viewModelScope.launch {
                val fetchedPosts = RetrofitInstance.api.getPosts()
                _posts.value = fetchedPosts
            }
        } catch (e: Exception) {
            Log.e("FeedViewModel", "Error fetching posts: ${e.message}")
            _posts.value = emptyList()
        }
    }

    fun publishPost(title: String, content: String) {

        val postRequest = PostRequest(
            title = title,
            content = content,
            publishedOn = getCurrentDateTime(), // ex. "2024-04-02T12:11:53.48Z"
            userId = USER_ID
        )

        viewModelScope.launch {
            val response = RetrofitInstance.api.publishPost(postRequest)
            if (response.isSuccessful) {
                Log.d("FeedViewModel", "Post published successfully")
            } else {
                Log.e("FeedViewModel", "Error creating post: ${response.errorBody()}")
            }
        }
    }
}

