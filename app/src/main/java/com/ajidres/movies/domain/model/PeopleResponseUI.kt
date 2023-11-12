package com.ajidres.movies.domain.model


data class PeopleResponseUI(
    val page: Int,
    val results: List<ResultUI>,
    val totalPages: Int,
)

data class ResultUI(
    val id: Int,
    val knownFor: String,
    val name: String,
    val profilePath: String,
    val rate:Int
){
    fun toMoviesList()
    : String {
        val lastComma=knownFor.lastIndexOf(',')
        return knownFor.replaceRange(lastComma, lastComma+1, " and")
    }
}
