package com.mohassan.homecompass.ui.main.chatbot

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.models.Message
import com.mohassan.homecompass.utils.BotResponse
import com.mohassan.homecompass.utils.Constants
import com.mohassan.homecompass.utils.Constants.OPEN_GOOGLE
import com.mohassan.homecompass.utils.Constants.OPEN_SEARCH
import com.mohassan.homecompass.utils.Constants.SEND_ID
import com.mohassan.homecompass.utils.Time
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatBotViewModel : ViewModel() {

    private val _messages = MutableLiveData<MutableList<Message>>()
    val messages: LiveData<MutableList<Message>>
        get() = _messages

    private val _openUrlEvent = MutableLiveData<String>()
    val openUrlEvent: LiveData<String>
        get() = _openUrlEvent

    val botList = listOf("Mohamed", "Osama", "Nadine", "Ghada","Abdalla")


    init {
        _messages.value = mutableListOf()
    }

    fun sendMessage(message: String) {
        val timeStamp = Time.timeStamp()
        val currentMessages = _messages.value ?: mutableListOf()

        if (message.isNotEmpty()) {
            currentMessages.add(Message(message, SEND_ID, timeStamp))
            _messages.value = currentMessages

            viewModelScope.launch {
                delay(1000)
                botResponse(message)
            }
        }
    }

    private suspend fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()
        val currentMessages = _messages.value ?: mutableListOf()

        //Fake response delay
        delay(1000)

        val response = BotResponse.basicResponses(message)
        currentMessages.add(Message(response, Constants.RECEIVE_ID, timeStamp))

        _messages.value = currentMessages

        when (response) {
            OPEN_GOOGLE -> {
                _openUrlEvent.value = "https://www.google.com/"
            }
            OPEN_SEARCH -> {
                val searchTerm: String? = message.substringAfterLast("search")
                _openUrlEvent.value = "https://www.google.com/search?&q=$searchTerm"
            }
            // Handle other cases if necessary
        }
    }

    fun sendCustomBotMessage(message: String) {
        val timeStamp = Time.timeStamp()
        val currentMessages = _messages.value ?: mutableListOf()

        viewModelScope.launch {
            delay(1000)
            currentMessages.add(Message(message, Constants.RECEIVE_ID, timeStamp))
            _messages.value = currentMessages
        }
    }
    fun clearMessages(){
        val currentMessages = _messages.value ?: mutableListOf()
        currentMessages.clear()
    }
}