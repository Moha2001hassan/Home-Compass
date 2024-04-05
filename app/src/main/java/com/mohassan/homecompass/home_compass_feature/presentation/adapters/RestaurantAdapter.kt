package com.mohassan.homecompass.home_compass_feature.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohassan.homecompass.R
import com.mohassan.homecompass.databinding.ItemRestaurantBinding
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.FacilitiesBody

class RestaurantAdapter(
    private val context: Context,
    private val restaurants: List<FacilitiesBody>
) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRestaurantBinding
            .inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }

    override fun getItemCount() = restaurants.size

    inner class ViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(restaurant: FacilitiesBody) {
            binding.txtRestAddress.text = restaurant.location
            binding.txtRestPhone.text = restaurant.phone

            Glide.with(binding.root.context)
                .load(restaurant.photoUrl)
                .centerCrop()
                .error(R.drawable.forget_pass)  // Error image if the URL is invalid
                .into(binding.imgRestaurant)

            binding.btnContact.setOnClickListener {
                val phoneNumber = restaurant.phone
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$phoneNumber")
                context.startActivity(intent)
            }
        }
    }
}