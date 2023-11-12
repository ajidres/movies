package com.ajidres.movies.data.proto.movies.upcoming

import com.ajidres.movies.domain.model.MoviesResponseUI
import com.ajidres.movies.domain.model.PeopleResponseUI
import kotlinx.coroutines.flow.Flow

interface ProtoUpcomingMoviesRepository {

    suspend fun getUpcomingMoviesData(): Flow<MoviesResponseUI>
    suspend fun saveUpcomingMoviesData(data: MoviesResponseUI)
    suspend fun clearAllData()


}