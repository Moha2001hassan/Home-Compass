package com.mohassan.homecompass.home_compass_feature.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mohassan.homecompass.home_compass_feature.presentation.fragments.FeedFragment
import com.mohassan.homecompass.home_compass_feature.presentation.fragments.HomeFragment
import com.mohassan.homecompass.home_compass_feature.presentation.fragments.RestaurantFragment
import com.mohassan.homecompass.home_compass_feature.presentation.fragments.ShelterFragment
import com.mohassan.homecompass.home_compass_feature.presentation.fragments.WorkFragment

class ViewPagerAdapter (fragment: HomeFragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FeedFragment()
            1 -> WorkFragment()
            2 -> ShelterFragment()
            else -> RestaurantFragment()
        }

    }
}