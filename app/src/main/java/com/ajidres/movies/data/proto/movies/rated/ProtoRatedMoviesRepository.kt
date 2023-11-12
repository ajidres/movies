package com.ajidres.movies.data.proto.movies.rated

import com.ajidres.movies.domain.model.MoviesResponseUI
import com.ajidres.movies.domain.model.PeopleResponseUI
import kotlinx.coroutines.flow.Flow

interface ProtoRatedMoviesRepository {

    suspend fun getRatedMoviesData(): Flow<MoviesResponseUI>
    suspend fun saveRatedMoviesData(data: MoviesResponseUI)
    suspend fun clearAllData()


}