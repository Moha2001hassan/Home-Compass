package com.mohassan.homecompass.home_compass_feature.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohassan.homecompass.databinding.MessageItemBinding
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.Message
import com.mohassan.homecompass.core.utils.Constants.RECEIVE_ID
import com.mohassan.homecompass.core.utils.Constants.SEND_ID

class ChatBotAdapter : RecyclerView.Adapter<ChatBotAdapter.MessageViewHolder>() {

    private var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder(val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = MessageItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.binding.tvMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.binding.tvBotMessage.visibility = View.GONE
            }

            RECEIVE_ID -> {
                holder.binding.tvBotMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.binding.tvMessage.visibility = View.GONE
            }
        }
    }

    fun setMessages(messages: List<Message>) {
        this.messagesList.clear()
        this.messagesList.addAll(messages)
        notifyDataSetChanged()
    }
}