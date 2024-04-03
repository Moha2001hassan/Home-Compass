package com.mohassan.homecompass.auth_feature.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mohassan.homecompass.R
import com.mohassan.homecompass.databinding.FragmentSignupOrSigninBinding

class SignUpOrSignInFragment : Fragment() {

    private lateinit var binding: FragmentSignupOrSigninBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignupOrSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signUpOrSignInFragment_to_signUpFragment)
        }

        binding.txtToSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpOrSignInFragment_to_signInFragment)
        }
    }


}