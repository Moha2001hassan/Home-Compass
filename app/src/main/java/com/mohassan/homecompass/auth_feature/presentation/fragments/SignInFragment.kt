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
import com.mohassan.homecompass.core.utils.Resource
import com.mohassan.homecompass.databinding.FragmentSignInBinding
import com.mohassan.homecompass.auth_feature.presentation.viewmodel.UserViewModel
import com.mohassan.homecompass.core.utils.ProgressDialog
import com.mohassan.homecompass.home_compass_feature.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: UserViewModel by viewModels()
    private val progressDialog by lazy { ProgressDialog.createProgressDialog(requireActivity()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtForgetPass.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgetPasswordFragment)
        }

        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpOrSignInFragment)
        }
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.loginUser(email, password)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        progressDialog.show()
                    }

                    is Resource.Success -> {
                        progressDialog.dismiss()
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                    }

                    is Resource.Error -> {
                        progressDialog.dismiss()
                        Toast.makeText(
                            requireContext(),
                            resource.message ?: "Login failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}