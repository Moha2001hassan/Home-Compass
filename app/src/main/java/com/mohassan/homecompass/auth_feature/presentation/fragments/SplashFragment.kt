package com.mohassan.homecompass.auth_feature.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mohassan.homecompass.R
import com.mohassan.homecompass.auth_feature.presentation.viewmodel.UserViewModel
import com.mohassan.homecompass.core.utils.Constants.SPLASH_SCREEN_DELAY
import com.mohassan.homecompass.home_compass_feature.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                Log.e("SplashFragment", "User is logged in and value is $isLoggedIn")
                navigateToMainActivity()
            } else {
                Log.e("SplashFragment", "User is not logged in")
                navigateToOnboardingFragment()
            }
        }
        return view
    }

    private fun navigateToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun navigateToOnboardingFragment() {
        lifecycleScope.launch {
            delay(SPLASH_SCREEN_DELAY)
            findNavController().navigate(R.id.action_splashFragment_to_onboarding1Fragment)
        }
    }
}