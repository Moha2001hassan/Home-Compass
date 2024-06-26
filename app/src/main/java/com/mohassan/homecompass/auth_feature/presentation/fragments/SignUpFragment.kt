package com.mohassan.homecompass.auth_feature.presentation.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mohassan.homecompass.R
import com.mohassan.homecompass.auth_feature.presentation.viewmodel.UserViewModel
import com.mohassan.homecompass.core.utils.ProgressDialog
import com.mohassan.homecompass.core.utils.Resource
import com.mohassan.homecompass.databinding.FragmentSignUpBinding
import com.mohassan.homecompass.home_compass_feature.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private val viewModel: UserViewModel by viewModels()
    private val progressDialog by lazy { ProgressDialog.createProgressDialog(requireActivity()) }

    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signUpOrSignInFragment)
        }
        binding.txtMoveLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
        binding.txtConfirmEmail.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_confirmEmailFragment)
        }

        // Observe registration status
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.registrationState.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        progressDialog.show()
                    }

                    is Resource.Success -> {
                        progressDialog.dismiss()
                        Toast.makeText(
                            requireActivity(),
                            "Registration is done successfully , please go to the Confirmation Screen",
                            Toast.LENGTH_LONG
                        ).show()
                        findNavController().navigate(R.id.confirmEmailFragment)

                    }

                    is Resource.Error -> {
                        progressDialog.dismiss()
                        Toast.makeText(
                            requireActivity(),
                            resource.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        binding.btnSignUp.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.registerUser(firstName, lastName, username, email, password)
        }
    }
}