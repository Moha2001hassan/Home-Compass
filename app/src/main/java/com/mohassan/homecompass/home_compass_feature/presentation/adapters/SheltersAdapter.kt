package com.mohassan.homecompass.home_compass_feature.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohassan.homecompass.R
import com.mohassan.homecompass.databinding.ItemShelterBinding
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.ShelterBody

class SheltersAdapter(
    private val context: Context,
    private val shelters: List<ShelterBody>
) : RecyclerView.Adapter<SheltersAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShelterBinding
            .inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shelters[position])
    }

    override fun getItemCount() = shelters.size

    inner class ViewHolder(private val binding: ItemShelterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(shelter: ShelterBody) {
            binding.txtShelterName.text = shelter.name
            binding.txtShelterAddress.text = shelter.address
        }
    }
}