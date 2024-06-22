package com.mohassan.homecompass.home_compass_feature.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.mohassan.homecompass.databinding.FragmentSearchMissingBinding
import com.mohassan.homecompass.home_compass_feature.presentation.ImageDisplayActivity

class SearchMissingFragment : Fragment() {

    private lateinit var binding: FragmentSearchMissingBinding
    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            showLoadingProgressBar()
            Handler(Looper.getMainLooper()).postDelayed({
                hideLoadingProgressBar()
                navigateToImageDisplay(uri)
            }, 5000)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchMissingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnUploadPhoto.setOnClickListener {
            galleryLauncher.launch("image/*")
        }
    }
    private fun showLoadingProgressBar() {
        // Show your loading progress bar
        // For simplicity, let's assume you have a ProgressBar in your layout
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoadingProgressBar() {
        // Hide your loading progress bar
        binding.progressBar.visibility = View.GONE
    }
    private fun navigateToImageDisplay(imageUri: Uri?) {
        // Navigate to ImageDisplayActivity to show the selected image
        val intent = Intent(requireContext(), ImageDisplayActivity::class.java)
        intent.putExtra(ImageDisplayActivity.EXTRA_IMAGE_URI, imageUri.toString())
        startActivity(intent)
    }



}