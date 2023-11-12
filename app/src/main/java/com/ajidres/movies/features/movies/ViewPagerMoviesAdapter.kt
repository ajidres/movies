package com.ajidres.movies.features.movies

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerMoviesAdapter(fm: FragmentManager, lc: Lifecycle) : FragmentStateAdapter(fm, lc) {

    val tabsTitle = listOf("Popular", "Top Rated", "Upcoming")

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0-> MoviesPosterFragment.newInstance(POPULAR_MOVIES)
            1 -> MoviesPosterFragment.newInstance(RATED_MOVIES)
            2 -> MoviesPosterFragment.newInstance(UPCOMING_MOVIES)
            else -> Fragment()
        }
    }
}

