package com.mohassan.homecompass.auth_feature.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.mohassan.homecompass.R
import com.mohassan.homecompass.auth_feature.presentation.viewmodel.UserViewModel
import com.mohassan.homecompass.databinding.ActivityIntroBinding
import com.mohassan.homecompass.home_compass_feature.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel.isLoggedIn.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                val navHostFragment =
                    supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(
                    R.id.signInFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.splashFragment, true)
                        .build()
                )         }
        }
    }
}