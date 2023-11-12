package com.ajidres.movies.data.api.repositories.movies

import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.domain.model.MoviesResponseUI
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun fetchPopularMovies(page:Int): Flow<ResultEndpoints<MoviesResponseUI>>
    fun fetchTopRatedMovies(page:Int): Flow<ResultEndpoints<MoviesResponseUI>>
    fun fetchUpcomingMovies(page:Int): Flow<ResultEndpoints<MoviesResponseUI>>

}