package com.mohassan.homecompass.home_compass_feature.presentation

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mohassan.homecompass.R
import com.mohassan.homecompass.databinding.ActivityImageDisplayBinding

class ImageDisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageUriString = intent.getStringExtra(EXTRA_IMAGE_URI)
        val imageUri = Uri.parse(imageUriString)

        binding.imageView.setImageURI(imageUri)
    }
    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
    }
}