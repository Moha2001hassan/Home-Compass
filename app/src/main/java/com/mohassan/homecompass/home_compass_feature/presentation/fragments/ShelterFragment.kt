package com.mohassan.homecompass.home_compass_feature.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohassan.homecompass.databinding.FragmentSheltersBinding
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.ShelterBody
import com.mohassan.homecompass.home_compass_feature.presentation.viewmodel.ShelterViewModel

class ShelterFragment : Fragment() {
    private lateinit var viewModel: ShelterViewModel
    //private lateinit var feedAdapter: FeedAdapter
    private val sheltersList = mutableListOf<ShelterBody>()
    private var _binding: FragmentSheltersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSheltersBinding.inflate(inflater, container, false)
        return binding.root
    }

}