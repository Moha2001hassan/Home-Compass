package com.mohassan.homecompass.ui.main.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mohassan.homecompass.ui.main.home.feed.FeedFragment
import com.mohassan.homecompass.ui.main.home.restaurants.RestaurantFragment
import com.mohassan.homecompass.ui.main.home.shelters.ShelterFragment
import com.mohassan.homecompass.ui.main.home.work.WorkFragment

class ViewPagerAdapter (fragment: HomeFragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FeedFragment()
            1 -> RestaurantFragment()
            2 -> ShelterFragment()
            else -> WorkFragment()
        }

    }
}