package com.ajidres.movies.data.proto.movies.popular

import com.ajidres.movies.domain.model.MoviesResponseUI
import kotlinx.coroutines.flow.Flow

interface ProtoPopularMoviesRepository {


    suspend fun getPopularMoviesData(): Flow<MoviesResponseUI>
    suspend fun savePopularMoviesData(data: MoviesResponseUI)
    suspend fun clearAllData()




}