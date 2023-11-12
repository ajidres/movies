package com.ajidres.movies.data.api.mapper

import com.ajidres.movies.data.api.model.MoviesResponse
import com.ajidres.movies.domain.model.MoviesResponseUI
import com.ajidres.movies.domain.model.ResultMoviesUI
import com.ajidres.movies.features.movies.POPULAR_MOVIES
import com.ajidres.movies.features.movies.RATED_MOVIES
import com.ajidres.movies.features.movies.UPCOMING_MOVIES
import okhttp3.internal.format


fun MoviesResponse.toMoviesResponseUI(sort:String): MoviesResponseUI =
    with(this) {
        return MoviesResponseUI(
            page = page,
            results = results.sortedByDescending {
                when(sort){
                    POPULAR_MOVIES -> it.voteAverage
                    RATED_MOVIES -> it.voteCount
                    UPCOMING_MOVIES -> it.releaseDate
                    else -> {}
                } as Comparable<Any>
            }.map { it ->
                ResultMoviesUI(
                    id = it.id,
                    originalTitle = it.originalTitle ?: it.title,
                    popularity = (it.voteAverage * 10).toInt(),
                    posterPath = "https://image.tmdb.org/t/p/original${it.posterPath}",
                    releaseDate = it.formatRelease(),
                    rated = it.voteCount
                )
            },
            totalPages = totalPages
        )
    }
