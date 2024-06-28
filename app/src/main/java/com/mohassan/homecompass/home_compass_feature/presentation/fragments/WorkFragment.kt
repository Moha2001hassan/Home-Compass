package com.mohassan.homecompass.home_compass_feature.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohassan.homecompass.auth_feature.presentation.viewmodel.UserViewModel
import com.mohassan.homecompass.databinding.FragmentWorkBinding
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.JobBody
import com.mohassan.homecompass.home_compass_feature.presentation.adapters.JobAdapter
import com.mohassan.homecompass.home_compass_feature.presentation.viewmodel.JobViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkFragment:Fragment() {
    private lateinit var viewModel: JobViewModel
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var jobAdapter: JobAdapter
    private val jobsList = mutableListOf<JobBody>()
    private var _binding: FragmentWorkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.isDonor.observe(viewLifecycleOwner) { isDonor ->
            binding.fabAddJob.visibility = if (isDonor) View.VISIBLE else View.GONE
        }

        setupViewModel()
        setupRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[JobViewModel::class.java]
        viewModel.jobs.observe(viewLifecycleOwner) { jobs ->
            jobsList.clear() // Clear the list before adding new posts
            jobsList.addAll(jobs)
            jobAdapter.notifyDataSetChanged()
        }
        viewModel.getJobs()
    }

    private fun setupRecyclerView() {
        jobAdapter = JobAdapter(requireContext(), jobsList)
        binding.rvJobs.adapter = jobAdapter
        binding.rvJobs.layoutManager = LinearLayoutManager(requireContext())
    }
}