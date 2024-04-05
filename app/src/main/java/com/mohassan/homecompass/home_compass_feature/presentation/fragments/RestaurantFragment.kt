package com.mohassan.homecompass.home_compass_feature.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohassan.homecompass.databinding.FragmentRestaurantsBinding
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.FacilitiesBody
import com.mohassan.homecompass.home_compass_feature.presentation.adapters.RestaurantAdapter
import com.mohassan.homecompass.home_compass_feature.presentation.viewmodel.RestaurantViewModel

class RestaurantFragment : Fragment() {
    private lateinit var viewModel: RestaurantViewModel
    private lateinit var restaurantAdapter: RestaurantAdapter
    private val restaurantsList = mutableListOf<FacilitiesBody>()
    private var _binding: FragmentRestaurantsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        //setupFab()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[RestaurantViewModel::class.java]
        viewModel.restaurants.observe(viewLifecycleOwner) { restaurants ->
            restaurantsList.clear() // Clear the list before adding new posts
            restaurantsList.addAll(restaurants)
            restaurantAdapter.notifyDataSetChanged()
        }
        viewModel.getRestaurants()
    }

    private fun setupRecyclerView() {
        restaurantAdapter = RestaurantAdapter(requireContext(), restaurantsList)
        binding.rvRestaurants.adapter = restaurantAdapter
        binding.rvRestaurants.layoutManager = LinearLayoutManager(requireContext())
    }
}