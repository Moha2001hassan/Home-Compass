package com.mohassan.homecompass.ui.main.chatbot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohassan.homecompass.models.Message
import com.mohassan.homecompass.utils.BotResponse
import com.mohassan.homecompass.utils.Constants
import com.mohassan.homecompass.utils.Time
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatBotViewModel : ViewModel() {

    private val _messages = MutableLiveData<MutableList<Message>>()
    val messages: LiveData<MutableList<Message>>
        get() = _messages

    val botList = listOf("Mohamed", "Osama", "Nadine", "Ghada","Abdalla")


    init {
        _messages.value = mutableListOf()
    }

    fun sendMessage(message: String) {
        val timeStamp = Time.timeStamp()
        val currentMessages = _messages.value ?: mutableListOf()

        if (message.isNotEmpty()) {
            currentMessages.add(Message(message, Constants.SEND_ID, timeStamp))
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

        // Handle special responses here...
        _messages.value = currentMessages
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