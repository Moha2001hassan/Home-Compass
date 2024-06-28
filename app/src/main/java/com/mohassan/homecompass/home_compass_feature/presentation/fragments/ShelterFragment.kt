package com.mohassan.homecompass.home_compass_feature.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohassan.homecompass.auth_feature.presentation.viewmodel.UserViewModel
import com.mohassan.homecompass.databinding.FragmentSheltersBinding
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.FacilitiesBody
import com.mohassan.homecompass.home_compass_feature.presentation.adapters.SheltersAdapter
import com.mohassan.homecompass.home_compass_feature.presentation.viewmodel.ShelterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShelterFragment : Fragment() {
    private lateinit var viewModel: ShelterViewModel
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var sheltersAdapter: SheltersAdapter
    private val sheltersList = mutableListOf<FacilitiesBody>()
    private var _binding: FragmentSheltersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSheltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.isDonor.observe(viewLifecycleOwner) { isDonor ->
            binding.fabAddShelter.visibility = if (isDonor) View.VISIBLE else View.GONE
        }

        setupViewModel()
        setupRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[ShelterViewModel::class.java]
        viewModel.shelters.observe(viewLifecycleOwner) { shelters ->
            sheltersList.clear() // Clear the list before adding new posts
            sheltersList.addAll(shelters)
            sheltersAdapter.notifyDataSetChanged()
        }
        viewModel.getShelters()
    }

    private fun setupRecyclerView() {
        sheltersAdapter = SheltersAdapter(requireContext(), sheltersList)
        binding.rvShelters.adapter = sheltersAdapter
        binding.rvShelters.layoutManager = LinearLayoutManager(requireContext())
    }
}