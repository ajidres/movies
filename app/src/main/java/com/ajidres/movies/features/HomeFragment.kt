package com.ajidres.movies.features

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ajidres.movies.R
import com.ajidres.movies.base.BaseFragment
import com.ajidres.movies.databinding.FragmentHomeBinding
import com.ajidres.movies.features.gallery.GalleryFragment
import com.ajidres.movies.features.locations.LocationFragment
import com.ajidres.movies.features.movies.MoviesFragment
import com.ajidres.movies.features.people.PeopleFragment

class HomeFragment:BaseFragment<FragmentHomeBinding>() {
    override fun initBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {

        bottomMenuSelection()
        binding.bottomNavigationView.selectedItemId= R.id.people
    }

    private fun bottomMenuSelection(){
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.people ->{
                    setFragment(PeopleFragment.newInstance())
                }
                R.id.movies ->{
                    setFragment(MoviesFragment.newInstance())
                }
                R.id.locations ->{
                    setFragment(LocationFragment.newInstance())
                }
                R.id.gallery ->{
                    setFragment(GalleryFragment.newInstance())
                }
                else -> {
                    true
                }
            }
        }
    }


    private fun setFragment(fragment: Fragment):Boolean{
        childFragmentManager.beginTransaction()
            .replace(R.id.flFragment, fragment)
            .commitAllowingStateLoss()
        return true
    }
}