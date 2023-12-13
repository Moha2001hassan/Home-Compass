package com.mohassan.homecompass.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.mohassan.homecompass.R

class SplashFragment : Fragment() {

    private val SPLASH_SCREEN_DELAY = 4000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)


        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_onboarding1Fragment)
        }, SPLASH_SCREEN_DELAY)

        return view
    }


}