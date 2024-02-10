package com.mohassan.homecompass.ui.main.chatbot

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohassan.homecompass.databinding.FragmentChatbotBinding

class ChatBotFragment : Fragment() {

    private var _binding: FragmentChatbotBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ChatBotAdapter
    private val viewModel: ChatBotViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatbotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.clearMessages()
        recyclerView()
        clickEvents()

        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            adapter.setMessages(messages)
            binding.rvMessages.scrollToPosition(messages.size - 1)
        }

        // welcome message
        val random = (0..4).random()
        viewModel.sendCustomBotMessage("Hello! Today you're speaking with " +
                "${viewModel.botList[random]}, how may I help?")

        viewModel.openUrlEvent.observe(viewLifecycleOwner) { url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun clickEvents() {
        binding.btnSend.setOnClickListener {
            val message = binding.etMessage.text.toString()
            if (message.isNotEmpty()){
                binding.animationView.visibility = View.GONE
                viewModel.sendMessage(message)
                binding.etMessage.setText("")
            }
        }

        binding.etMessage.setOnClickListener {
            binding.rvMessages.scrollToPosition(adapter.itemCount - 1)
        }
    }

    private fun recyclerView() {
        adapter = ChatBotAdapter()
        binding.rvMessages.adapter = adapter
        binding.rvMessages.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}