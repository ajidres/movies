package com.ajidres.movies.features.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.domain.model.MoviesResponseUI
import com.ajidres.movies.domain.useCases.movies.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase): ViewModel() {

    private var _moviesDataApi = MutableLiveData<ResultEndpoints<MoviesResponseUI>>()
    val moviesDataApi: LiveData<ResultEndpoints<MoviesResponseUI>> get() = _moviesDataApi

    private var _moviesDataDb = MutableLiveData<MoviesResponseUI>()
    val moviesDataDb: LiveData<MoviesResponseUI> get() = _moviesDataDb

    fun fetchPopularMovies() {
        viewModelScope.launch {
            moviesUseCase.invokeFetchPopularMovies(1).collect {
                _moviesDataApi.value = it
            }
        }
    }

    fun fetchRatedMovies() {
        viewModelScope.launch {
            moviesUseCase.invokeFetchRatedMovies(1).collect {
                _moviesDataApi.value = it
            }
        }
    }

    fun fetchUpcomingMovies() {
        viewModelScope.launch {
            moviesUseCase.invokeFetchUpcomingMovies(1).collect {
                _moviesDataApi.value = it
            }
        }
    }

    fun getPopularMoviesDb(){
        viewModelScope.launch {
            moviesUseCase.fetchPopularMovies().collect {
                _moviesDataDb.value = it
            }
        }
    }

    fun getRatedMoviesDb(){
        viewModelScope.launch {
            moviesUseCase.fetchRatedMovies().collect {
                _moviesDataDb.value = it
            }
        }
    }

    fun getUpcomingMoviesDb(){
        viewModelScope.launch {
            moviesUseCase.fetchUpcomingMovies().collect {
                _moviesDataDb.value = it
            }
        }
    }

    fun savePopularMovies(data: MoviesResponseUI) {
        viewModelScope.launch {
            moviesUseCase.savePopularMovies(data)
        }
    }

    fun saveRatedMovies(data: MoviesResponseUI) {
        viewModelScope.launch {
            moviesUseCase.savePopularMovies(data)
        }
    }

    fun saveUpcomingMovies(data: MoviesResponseUI) {
        viewModelScope.launch {
            moviesUseCase.savePopularMovies(data)
        }
    }
}