package com.ajidres.movies.data.proto.mapper

import com.ajidres.movies.MoviesList
import com.ajidres.movies.PopularMovies
import com.ajidres.movies.RatedMovies
import com.ajidres.movies.UpcomingMovies
import com.ajidres.movies.data.api.model.MoviesResponse
import com.ajidres.movies.domain.model.MoviesResponseUI
import com.ajidres.movies.domain.model.PeopleResponseUI
import com.ajidres.movies.domain.model.ResultMoviesUI
import com.ajidres.movies.domain.model.ResultUI

fun PopularMovies.toMoviesResponseUI(): MoviesResponseUI {
    with(this){
        return MoviesResponseUI(
            page=page,
            results = moviesListList.map { ResultMoviesUI(
                id= it.id,
                originalTitle= it.originalTitle,
                popularity= it.popularity,
                posterPath= it.posterPath,
                releaseDate = it.releaseDate,
                rated = it.rated
            ) },
            totalPages=totalPages
        )
    }
}

fun RatedMovies.toMoviesResponseUI(): MoviesResponseUI {
    with(this){
        return MoviesResponseUI(
            page=page,
            results = moviesListList.map { ResultMoviesUI(
                id= it.id,
                originalTitle= it.originalTitle,
                popularity= it.popularity,
                posterPath= it.posterPath,
                releaseDate = it.releaseDate,
                rated = it.rated
            ) },
            totalPages=totalPages
        )
    }
}

fun UpcomingMovies.toMoviesResponseUI(): MoviesResponseUI {
    with(this){
        return MoviesResponseUI(
            page=page,
            results = moviesListList.map { ResultMoviesUI(
                id= it.id,
                originalTitle= it.originalTitle,
                popularity= it.popularity,
                posterPath= it.posterPath,
                releaseDate = it.releaseDate,
                rated = it.rated
            ) },
            totalPages=totalPages
        )
    }
}

fun List<ResultMoviesUI>.toMoviesList():List<MoviesList>{
    with(this){
        return map {
            MoviesList.newBuilder()
                .setId(it.id)
                .setOriginalTitle(it.originalTitle)
                .setPopularity(it.popularity)
                .setPosterPath(it.posterPath)
                .build()
        }.toList()
    }
}
