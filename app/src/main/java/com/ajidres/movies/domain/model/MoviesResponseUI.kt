package com.ajidres.movies.domain.model

import com.google.gson.annotations.SerializedName

data class MoviesResponseUI(
    val page: Int,
    val results: List<ResultMoviesUI>,
    val totalPages: Int
)

data class ResultMoviesUI(
    val id: Int,
    val originalTitle: String,
    val popularity: Int,
    val rated: Int,
    val posterPath: String,
    val releaseDate: String,
){
    fun showPopularity(): String {
        return "${popularity}%"
    }
}