package com.ajidres.movies.domain.useCases.movies

import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.domain.model.MoviesResponseUI
import com.ajidres.movies.domain.model.PeopleResponseUI
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {

    fun invokeFetchPopularMovies(page:Int): Flow<ResultEndpoints<MoviesResponseUI>>
    suspend fun savePopularMovies(data: MoviesResponseUI)
    suspend fun fetchPopularMovies():Flow<MoviesResponseUI>

    fun invokeFetchRatedMovies(page:Int): Flow<ResultEndpoints<MoviesResponseUI>>
    suspend fun saveRatedMovies(data: MoviesResponseUI)
    suspend fun fetchRatedMovies():Flow<MoviesResponseUI>

    fun invokeFetchUpcomingMovies(page:Int): Flow<ResultEndpoints<MoviesResponseUI>>
    suspend fun saveUpcomingMovies(data: MoviesResponseUI)
    suspend fun fetchUpcomingMovies():Flow<MoviesResponseUI>

}