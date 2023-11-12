package com.ajidres.movies.data.proto.movies.popular

import androidx.datastore.core.DataStore
import com.ajidres.movies.PopularMovies
import com.ajidres.movies.data.proto.mapper.toMoviesList
import com.ajidres.movies.data.proto.mapper.toMoviesResponseUI
import com.ajidres.movies.domain.model.MoviesResponseUI
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProtoPopularMoviesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<PopularMovies>
) : ProtoPopularMoviesRepository {


    override suspend fun getPopularMoviesData(): Flow<MoviesResponseUI> {
        return dataStore.data.map { it.toMoviesResponseUI() }
    }

    override suspend fun savePopularMoviesData(data: MoviesResponseUI) {
        dataStore.updateData { movies: PopularMovies ->
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
        dataStore.updateData { movies: PopularMovies ->
            movies.toBuilder().clear().build()
        }
    }
}