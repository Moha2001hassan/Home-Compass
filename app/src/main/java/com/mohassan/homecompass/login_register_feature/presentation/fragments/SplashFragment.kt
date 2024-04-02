package com.mohassan.homecompass.login_register_feature.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mohassan.homecompass.R
import com.mohassan.homecompass.core.utils.Constants.LOGIN_VALUE
import com.mohassan.homecompass.core.utils.Constants.PREF_IS_LOGIN
import com.mohassan.homecompass.core.utils.Constants.SHARED_PREF
import com.mohassan.homecompass.core.utils.Constants.SPLASH_SCREEN_DELAY
import com.mohassan.homecompass.home_compass_feature.presentation.MainActivity

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        // Check if the user is already logged in
        val sharedPreferences =
            requireActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        val isLogin = sharedPreferences.getString(PREF_IS_LOGIN, "")

        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_onboarding1Fragment)
            /*
            if (isLogin == LOGIN_VALUE) {
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_onboarding1Fragment)
            }
            */
        }, SPLASH_SCREEN_DELAY)
        return view
    }
}