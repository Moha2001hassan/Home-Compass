package com.mohassan.homecompass.home_compass_feature.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohassan.homecompass.databinding.ItemJobBinding
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.JobBody

class JobAdapter(
    private val context: Context,
    private val jobs: List<JobBody>
) : RecyclerView.Adapter<JobAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemJobBinding
            .inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(jobs[position])
    }

    override fun getItemCount() = jobs.size

    inner class ViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: JobBody) {
            binding.txtJobTitle.text = job.title
            binding.txtJobDescription.text = job.description
            binding.txtJobPhone.text = job.contactInformation
            binding.txtJobAddress.text = job.location
            binding.txtJobSalary.text = job.salary
            binding.txtHours.text = job.hours
        }
    }
}