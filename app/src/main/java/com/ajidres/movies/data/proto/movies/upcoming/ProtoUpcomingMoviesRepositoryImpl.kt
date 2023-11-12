package com.ajidres.movies.data.proto.movies.upcoming

import androidx.datastore.core.DataStore
import com.ajidres.movies.UpcomingMovies
import com.ajidres.movies.data.proto.mapper.toMoviesList
import com.ajidres.movies.data.proto.mapper.toMoviesResponseUI
import com.ajidres.movies.domain.model.MoviesResponseUI
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProtoUpcomingMoviesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<UpcomingMovies>
) : ProtoUpcomingMoviesRepository {


    override suspend fun getUpcomingMoviesData(): Flow<MoviesResponseUI> {
        return dataStore.data.map { it.toMoviesResponseUI() }
    }

    override suspend fun saveUpcomingMoviesData(data: MoviesResponseUI) {
        dataStore.updateData { movies: UpcomingMovies ->
            with(data){
                movies.toBuilder().clear()
                    .setPage(page)
                    .setTotalPages(totalPages)
                    .addAllMoviesList(data.results.toMoviesList())
                    .build()
            }

        }
    }

    override suspend fun clearAllData() {
        dataStore.updateData { movies: UpcomingMovies ->
            movies.toBuilder().clear().build()
        }
    }
}