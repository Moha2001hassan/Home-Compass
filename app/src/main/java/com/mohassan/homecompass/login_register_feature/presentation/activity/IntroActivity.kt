package com.mohassan.homecompass.login_register_feature.presentation.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mohassan.homecompass.core.utils.Constants.SHARED_PREF
import com.mohassan.homecompass.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val sharedPreferences = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)



    }
}