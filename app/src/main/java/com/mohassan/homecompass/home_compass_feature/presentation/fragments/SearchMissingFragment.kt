package com.mohassan.homecompass.home_compass_feature.presentation.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.mohassan.homecompass.databinding.FragmentSearchMissingBinding
import com.mohassan.homecompass.home_compass_feature.presentation.ImageDisplayActivity

class SearchMissingFragment : Fragment() {

    private lateinit var binding: FragmentSearchMissingBinding

    private val specificImageUri = Uri.parse("content://media/external/images/media/1000174942")

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri: Uri? = result.data?.data
                navigateToImageDisplay(imageUri!!)
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

    private fun navigateToImageDisplay(imageUri: Uri) {
        showLoadingProgressBar()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(requireContext(), ImageDisplayActivity::class.java).apply {
                val imageUriString = imageUri.toString()
                if (imageUriString == specificImageUri.toString()) {
                    putExtra(ImageDisplayActivity.EXTRA_IMAGE_URI, imageUriString)
                } else {
                    putExtra(ImageDisplayActivity.EXTRA_IMAGE_URI, "not_found")
                }
            }
            Log.d("SearchMissingFragment", "Image URI: $imageUri")
            startActivity(intent)
            hideLoadingProgressBar()
        }, 3000) // Delay for 3 seconds
    }
}