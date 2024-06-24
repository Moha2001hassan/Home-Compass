package com.mohassan.homecompass.auth_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mohassan.homecompass.R
import com.mohassan.homecompass.auth_feature.presentation.viewmodel.ConfirmationEmailViewModel
import com.mohassan.homecompass.core.utils.ProgressDialog
import com.mohassan.homecompass.core.utils.Resource
import com.mohassan.homecompass.databinding.FragmentConfirmEmailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConfirmEmailFragment :Fragment(){
    private val viewModel: ConfirmationEmailViewModel by viewModels()

    private val progressDialog by lazy { ProgressDialog.createProgressDialog(requireActivity()) }

    private lateinit var binding: FragmentConfirmEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBackConfirmEmail.setOnClickListener {
            findNavController().navigateUp()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.confirmEmailState.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        progressDialog.dismiss()
                        Toast.makeText(requireContext(), "Confirmation is done successfully", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_confirmEmailFragment_to_signInFragment)
                    }
                    is Resource.Error -> {
                        progressDialog.dismiss()
                        Toast.makeText(requireContext(), resource.message ?: "Unknown error", Toast.LENGTH_LONG).show()
                    }
                    is Resource.Loading -> {
                        progressDialog.show()
                    }
                }
            }
        }

        binding.btnSubmit.setOnClickListener {
            val email = binding.etEmailConfirm.text.toString()
            val token = binding.etToken.text.toString()

            viewModel.confirmEmail(email, token)
        }
    }
}