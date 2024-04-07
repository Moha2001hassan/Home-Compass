package com.mohassan.homecompass.home_compass_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.mohassan.homecompass.databinding.FragmentSearchMissingBinding

class SearchMissingFragment : Fragment() {

    private lateinit var binding: FragmentSearchMissingBinding
    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { _ ->
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

}