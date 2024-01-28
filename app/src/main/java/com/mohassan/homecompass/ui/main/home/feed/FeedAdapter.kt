package com.mohassan.homecompass.ui.main.home.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohassan.homecompass.databinding.ItemPostBinding
import com.mohassan.homecompass.models.Post


class FeedAdapter(
    private val context: Context,
    private val posts: List<Post>
) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

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

        fun bind(post: Post) {
            //binding.tvId.text = "Post id is : ${post.id}"
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body

        }
    }
}