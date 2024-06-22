package com.mohassan.homecompass.home_compass_feature.presentation.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.mohassan.homecompass.databinding.FragmentSearchMissingBinding
import com.mohassan.homecompass.home_compass_feature.presentation.ImageDisplayActivity

class SearchMissingFragment : Fragment() {

    private lateinit var binding: FragmentSearchMissingBinding

    private val specificImageUri = Uri.parse("/storage/emulated/0/Download/True_person.jpeg")

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri: Uri? = result.data?.data
                navigateToImageDisplay(imageUri)
            }
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
            openGallery()
        }
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(intent)
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
        // Navigate to ImageDisplayActivity to show the selected image and details
        val intent = Intent(requireContext(), ImageDisplayActivity::class.java).apply {
            if (imageUri == specificImageUri) {
                putExtra(ImageDisplayActivity.EXTRA_IMAGE_URI, imageUri.toString())
            } else {
                // If doesn't match, display "not found" image and message
                putExtra(ImageDisplayActivity.EXTRA_IMAGE_URI, "not_found")
            }
        }
        startActivity(intent)
    }



}