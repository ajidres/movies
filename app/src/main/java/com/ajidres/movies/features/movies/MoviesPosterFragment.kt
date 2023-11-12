package com.ajidres.movies.features.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ajidres.movies.base.BaseFragment
import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.databinding.FragmentMoviesFilterBinding
import com.ajidres.movies.domain.model.MoviesResponseUI
import dagger.hilt.android.AndroidEntryPoint


const val TYPE_MOVIES = "TYPE_MOVIES"
const val POPULAR_MOVIES = "POPULAR_MOVIES"
const val RATED_MOVIES = "RATED_MOVIES"
const val UPCOMING_MOVIES = "UPCOMING_MOVIES"

@AndroidEntryPoint
class MoviesPosterFragment : BaseFragment<FragmentMoviesFilterBinding>() {

    private val viewModel: MoviesViewModel by viewModels()

    private val moviesAdapter by lazy { MoviesAdapter() }

    private lateinit var movieType: String

    override fun initBinding(): FragmentMoviesFilterBinding = FragmentMoviesFilterBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        progressBar = binding.progressBar.root
        movieType = arguments?.getString(TYPE_MOVIES)!!

        setupPeopleRecycler()
        moviesDataObserverApi()
        moviesDataObserverDb()
        getMoviesDb()

    }

    private fun requestMovies() {
        when (movieType) {
            POPULAR_MOVIES -> viewModel.fetchPopularMovies()
            RATED_MOVIES -> viewModel.fetchRatedMovies()
            UPCOMING_MOVIES -> viewModel.fetchUpcomingMovies()
        }
    }

    private fun moviesDataObserverApi() {
        viewModel.moviesDataApi.observe(this) { movies ->
            when (movies) {
                is ResultEndpoints.Loading -> {
                    showProgress(movies.show)
                }

                is ResultEndpoints.Success -> {
                    saveMoviesDb(movies.data)
                    moviesAdapter.update(movies.data.results,movieType)
                }

                is ResultEndpoints.Failure -> {
                    Toast.makeText(requireActivity(), movies.errorMessage!!.description, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun moviesDataObserverDb() {
        viewModel.moviesDataDb.observe(this) { result ->
            requestMovies()
            moviesAdapter.update(result.results, movieType)
        }
    }

    private fun saveMoviesDb(data: MoviesResponseUI) {
        when (movieType) {
            POPULAR_MOVIES -> viewModel.savePopularMovies(data)
            RATED_MOVIES -> viewModel.saveRatedMovies(data)
            UPCOMING_MOVIES -> viewModel.saveUpcomingMovies(data)
        }
    }

    private fun getMoviesDb() {
        when (movieType) {
            POPULAR_MOVIES -> viewModel.getPopularMoviesDb()
            RATED_MOVIES -> viewModel.getRatedMoviesDb()
            UPCOMING_MOVIES -> viewModel.getUpcomingMoviesDb()
        }
    }

    private fun setupPeopleRecycler() {
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = moviesAdapter
        }
    }

    companion object {
        fun newInstance(typeMovies: String): MoviesPosterFragment {
            val fragment = MoviesPosterFragment().apply {
                arguments = Bundle().apply {
                    putString(TYPE_MOVIES, typeMovies)
                }
            }
            return fragment
        }
    }
}