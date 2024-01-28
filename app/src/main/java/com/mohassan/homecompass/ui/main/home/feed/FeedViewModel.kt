package com.mohassan.homecompass.ui.main.home.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.data.remote.RetrofitInstance
import com.mohassan.homecompass.models.Post
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>>
        get() = _posts

    init {
        getPosts()
    }

    fun getPosts() {
        viewModelScope.launch {
            val fetchedPosts = RetrofitInstance.api.getPosts()
            _posts.value = fetchedPosts
        }

    }
}

