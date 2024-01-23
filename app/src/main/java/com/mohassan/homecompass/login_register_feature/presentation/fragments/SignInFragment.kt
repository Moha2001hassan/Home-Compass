package com.mohassan.homecompass.login_register_feature.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mohassan.homecompass.R
import com.mohassan.homecompass.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
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
    }

}