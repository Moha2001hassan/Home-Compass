package com.mohassan.homecompass.ui.main.home.feed

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohassan.homecompass.databinding.FragmentFeedBinding
import com.mohassan.homecompass.models.Post
import com.mohassan.homecompass.ui.main.searchMissing.SearchMissingViewModel

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
        // Inflate the layout for this fragment
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]

        viewModel.posts.observe(requireActivity(), Observer { posts ->
            Log.i(ContentValues.TAG, "Number of Posts are : ${posts.size}")
            postsList.addAll(posts)
            feedAdapter.notifyDataSetChanged()
        })

        feedAdapter = FeedAdapter(requireContext(),postsList)
        binding.rvPosts.adapter = feedAdapter
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getPosts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}











