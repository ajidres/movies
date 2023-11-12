package com.ajidres.movies.features.movies

import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import com.ajidres.movies.base.BaseFragment
import com.ajidres.movies.databinding.FragmentMoviesBinding
import com.ajidres.movies.features.people.PeopleAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    private val moviesAdapter by lazy { ViewPagerMoviesAdapter(childFragmentManager, lifecycle) }

    override fun initBinding(): FragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        setViewPagerMovies()
    }

    private fun setViewPagerMovies(){
        binding.vpMovies.adapter = moviesAdapter
        TabLayoutMediator(binding.tlMovies, binding.vpMovies) { tab, position ->
            tab.text = moviesAdapter.tabsTitle[position]
        }.attach()
    }

    companion object {
        fun newInstance(): MoviesFragment {
            return MoviesFragment()
        }
    }
}