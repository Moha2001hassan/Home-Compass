package com.mohassan.homecompass.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mohassan.homecompass.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()



    }
}