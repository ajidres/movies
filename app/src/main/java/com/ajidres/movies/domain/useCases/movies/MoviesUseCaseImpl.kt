package com.ajidres.movies.domain.useCases.movies

import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.data.api.repositories.movies.MoviesRepository
import com.ajidres.movies.data.proto.movies.popular.ProtoPopularMoviesRepository
import com.ajidres.movies.data.proto.movies.popular.ProtoPopularMoviesRepositoryImpl
import com.ajidres.movies.data.proto.movies.rated.ProtoRatedMoviesRepository
import com.ajidres.movies.data.proto.movies.rated.ProtoRatedMoviesRepositoryImpl
import com.ajidres.movies.data.proto.movies.upcoming.ProtoUpcomingMoviesRepository
import com.ajidres.movies.data.proto.movies.upcoming.ProtoUpcomingMoviesRepositoryImpl
import com.ajidres.movies.domain.model.MoviesResponseUI
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class MoviesUseCaseImpl @Inject constructor(private val apiRepository: MoviesRepository,
                                            private val dbPopularMoviesRepository: ProtoPopularMoviesRepository,
                                            private val dbRatedMoviesRepository: ProtoRatedMoviesRepository,
                                            private val dbUpcomingMoviesRepository: ProtoUpcomingMoviesRepository,
): MoviesUseCase {


    override fun invokeFetchPopularMovies(page: Int): Flow<ResultEndpoints<MoviesResponseUI>> = apiRepository.fetchPopularMovies(page)
    override suspend fun savePopularMovies(data: MoviesResponseUI) = dbPopularMoviesRepository.savePopularMoviesData(data)
    override suspend fun fetchPopularMovies(): Flow<MoviesResponseUI> = dbPopularMoviesRepository.getPopularMoviesData()

    override fun invokeFetchRatedMovies(page: Int): Flow<ResultEndpoints<MoviesResponseUI>> = apiRepository.fetchTopRatedMovies(page)
    override suspend fun saveRatedMovies(data: MoviesResponseUI) = dbRatedMoviesRepository.saveRatedMoviesData(data)
    override suspend fun fetchRatedMovies(): Flow<MoviesResponseUI> = dbRatedMoviesRepository.getRatedMoviesData()

    override fun invokeFetchUpcomingMovies(page: Int): Flow<ResultEndpoints<MoviesResponseUI>> = apiRepository.fetchUpcomingMovies(page)
    override suspend fun saveUpcomingMovies(data: MoviesResponseUI) =dbUpcomingMoviesRepository.saveUpcomingMoviesData(data)
    override suspend fun fetchUpcomingMovies(): Flow<MoviesResponseUI> = dbUpcomingMoviesRepository.getUpcomingMoviesData()

}