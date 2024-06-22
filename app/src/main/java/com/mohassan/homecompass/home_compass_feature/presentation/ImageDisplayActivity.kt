package com.mohassan.homecompass.home_compass_feature.presentation

import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mohassan.homecompass.R
import com.mohassan.homecompass.databinding.ActivityImageDisplayBinding

class ImageDisplayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageUriString = intent.getStringExtra(EXTRA_IMAGE_URI)

        if (imageUriString == "not_found") {
            // Display "not found" image and message
            binding.imageView.setImageResource(R.drawable.not_found_image)
            binding.textView.text = "Not found!"
            binding.textAddress.visibility = View.INVISIBLE
            binding.textAge.visibility = View.INVISIBLE
            binding.textName.visibility = View.INVISIBLE
            binding.textPhone.visibility = View.INVISIBLE

        } else {
            val imageUri = Uri.parse(imageUriString)
            binding.imageView.setImageURI(imageUri)

        }
    }
    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
    }
}