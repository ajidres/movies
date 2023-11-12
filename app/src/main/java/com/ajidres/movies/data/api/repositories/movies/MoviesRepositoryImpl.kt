package com.ajidres.movies.data.api.repositories.movies

import com.ajidres.movies.data.api.EndPoints
import com.ajidres.movies.data.api.mapper.toMoviesResponseUI
import com.ajidres.movies.data.api.mapper.toPeopleResponseUI
import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.domain.model.MoviesResponseUI
import com.ajidres.movies.extentions.bodyOrException
import com.ajidres.movies.extentions.getErrorResponse
import com.ajidres.movies.features.movies.POPULAR_MOVIES
import com.ajidres.movies.features.movies.RATED_MOVIES
import com.ajidres.movies.features.movies.UPCOMING_MOVIES
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class MoviesRepositoryImpl  @Inject constructor(private val apis: EndPoints) : MoviesRepository {


    override fun fetchPopularMovies(page: Int): Flow<ResultEndpoints<MoviesResponseUI>> {
        return flow<ResultEndpoints<MoviesResponseUI>> {
            val response = apis.popularMovies(page = page).bodyOrException()
            emit(ResultEndpoints.Success(response.toMoviesResponseUI(POPULAR_MOVIES)))
        }.onStart {
            emit(ResultEndpoints.Loading(true))
        }.onCompletion {
            emit(ResultEndpoints.Loading(false))
        }.catch {
            emit(ResultEndpoints.Loading(false))
            emit(ResultEndpoints.Failure(errorMessage = it.getErrorResponse()))
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchTopRatedMovies(page: Int): Flow<ResultEndpoints<MoviesResponseUI>> {
        return flow<ResultEndpoints<MoviesResponseUI>> {
            val response = apis.ratedMovies(page = page).bodyOrException()
            emit(ResultEndpoints.Success(response.toMoviesResponseUI(RATED_MOVIES)))
        }.onStart {
            emit(ResultEndpoints.Loading(true))
        }.onCompletion {
            emit(ResultEndpoints.Loading(false))
        }.catch {
            emit(ResultEndpoints.Loading(false))
            emit(ResultEndpoints.Failure(errorMessage = it.getErrorResponse()))
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchUpcomingMovies(page: Int): Flow<ResultEndpoints<MoviesResponseUI>> {
        return flow<ResultEndpoints<MoviesResponseUI>> {
            val response = apis.upcomingMovies(page = page).bodyOrException()
            emit(ResultEndpoints.Success(response.toMoviesResponseUI(UPCOMING_MOVIES)))
        }.onStart {
            emit(ResultEndpoints.Loading(true))
        }.onCompletion {
            emit(ResultEndpoints.Loading(false))
        }.catch {
            emit(ResultEndpoints.Loading(false))
            emit(ResultEndpoints.Failure(errorMessage = it.getErrorResponse()))
        }.flowOn(Dispatchers.IO)
    }


}