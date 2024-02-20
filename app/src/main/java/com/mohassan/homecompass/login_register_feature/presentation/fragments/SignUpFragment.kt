package com.mohassan.homecompass.login_register_feature.presentation.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mohassan.homecompass.R
import com.mohassan.homecompass.core.utils.Constants
import com.mohassan.homecompass.core.utils.Constants.LOGIN_VALUE
import com.mohassan.homecompass.core.utils.Constants.PREF_EMAIL_KEY
import com.mohassan.homecompass.core.utils.Constants.PREF_IS_LOGIN
import com.mohassan.homecompass.core.utils.Constants.PREF_NAME_KEY
import com.mohassan.homecompass.core.utils.Constants.PREF_PHONE_KEY
import com.mohassan.homecompass.databinding.FragmentSignUpBinding
import com.mohassan.homecompass.home_compass_feature.presentation.MainActivity

class SignUpFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences
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

        sharedPref =
            requireContext().getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)

        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signUpOrSignInFragment)
        }

        binding.btnSignUp.setOnClickListener {
            sharedPref.edit()
                .putString(PREF_NAME_KEY, binding.etName.text.toString())
                .putString(PREF_PHONE_KEY, binding.etPhone.text.toString())
                .putString(PREF_EMAIL_KEY, binding.etEmail.text.toString())
                .putString(PREF_IS_LOGIN, LOGIN_VALUE)
                .apply()


            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Finish the current activity
        }
    }
}