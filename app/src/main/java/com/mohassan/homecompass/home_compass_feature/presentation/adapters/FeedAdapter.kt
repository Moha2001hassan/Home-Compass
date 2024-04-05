package com.mohassan.homecompass.home_compass_feature.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohassan.homecompass.R
import com.mohassan.homecompass.databinding.ItemPostBinding
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.Post

class FeedAdapter(
    private val context: Context,
    private val posts: List<Post>
) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPostBinding
            .inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    inner class ViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var postKey: String
        private var isLiked = false

        @SuppressLint("SetTextI18n")
        fun bind(post: Post) {
            postKey = "isLiked_${post.id}"
            isLiked = sharedPreferences.getBoolean(postKey, false)

            binding.tvName.text = post.authorName
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.content
            binding.tvCommentsCount.text = post.commentsCount.toString()
            updateLike(post)

            Glide.with(binding.root.context)
                .load(post.authorPhotoUrl)
                .centerCrop()
                .error(R.drawable.profile_img)  // Error image if the URL is invalid
                .into(binding.imageView)

            binding.imgLike.setOnClickListener {
                isLiked = !isLiked
                updateLike(post)

                sharedPreferences.edit {
                    putBoolean(postKey, isLiked)
                    apply()
                }
            }
        }

        @SuppressLint("SetTextI18n")
        private fun updateLike(post: Post) {
            if (isLiked) {
                binding.imgLike.setImageResource(R.drawable.ic_filled_like)
                binding.tvLikesCount.text = (post.likesCount + 1).toString()
            } else {
                binding.imgLike.setImageResource(R.drawable.ic_like)
                binding.tvLikesCount.text = post.likesCount.toString()
            }
        }
    }
}