package com.mohassan.homecompass.home_compass_feature.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mohassan.homecompass.R
import com.mohassan.homecompass.databinding.FragmentFeedBinding
import com.mohassan.homecompass.home_compass_feature.data.remote.dto.Post
import com.mohassan.homecompass.home_compass_feature.presentation.adapters.FeedAdapter
import com.mohassan.homecompass.home_compass_feature.presentation.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private lateinit var feedAdapter: FeedAdapter
    private val postsList = mutableListOf<Post>()
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        setupFab()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            postsList.addAll(posts)
            feedAdapter.notifyDataSetChanged()
        }
        viewModel.getPosts()
    }

    private fun setupRecyclerView() {
        feedAdapter = FeedAdapter(requireContext(), postsList)
        binding.rvPosts.adapter = feedAdapter
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupFab() {
        binding.fabAddPost.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.post_bottom_sheet_layout, null)
        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
        val btnPost = view.findViewById<Button>(R.id.idBtnPost)

        btnClose.setOnClickListener { dialog.dismiss() }
        btnPost.setOnClickListener {
            publishPost(view)
            dialog.dismiss()
        }

        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
    }

    private fun publishPost(view: View) {
        try {
            val postTitle = view.findViewById<EditText>(R.id.etPostTitle).text.toString()
            val postContent = view.findViewById<EditText>(R.id.etPostContent).text.toString()
            viewModel.publishPost(postTitle, postContent)
        } catch (e: Exception) {
            Log.e("FeedViewModel", "Error creating post: $e")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



