package com.ajidres.movies.data.proto.movies.rated

import androidx.datastore.core.DataStore
import com.ajidres.movies.RatedMovies
import com.ajidres.movies.data.proto.mapper.toMoviesList
import com.ajidres.movies.data.proto.mapper.toMoviesResponseUI
import com.ajidres.movies.domain.model.MoviesResponseUI
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProtoRatedMoviesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<RatedMovies>
) : ProtoRatedMoviesRepository {

    override suspend fun getRatedMoviesData(): Flow<MoviesResponseUI> {
        return dataStore.data.map { it.toMoviesResponseUI() }
    }

    override suspend fun saveRatedMoviesData(data: MoviesResponseUI) {
        dataStore.updateData { movies: RatedMovies ->
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
        dataStore.updateData { movies: RatedMovies ->
            movies.toBuilder().clear().build()
        }
    }
}