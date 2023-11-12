package com.ajidres.movies.data.api

import com.ajidres.movies.BuildConfig
import com.ajidres.movies.data.api.model.MoviesResponse
import com.ajidres.movies.data.api.model.PeopleResponse
import java.util.Locale
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val PAGE_QUERY ="page"
const val LANGUAGE_QUERY ="language"
const val AUTHORIZATION_HEADER = "Authorization"
const val PREFIX_TOKEN="Bearer"

interface EndPoints {

    @GET("person/popular")
    suspend fun people(@Header(AUTHORIZATION_HEADER) authorization:String="$PREFIX_TOKEN ${BuildConfig.ACCESS_TOKEN}",
                       @Query(LANGUAGE_QUERY) language:String = Locale.getDefault().isO3Language,
                       @Query(PAGE_QUERY) page:Int = 1): Response<PeopleResponse>

    @GET("movie/popular")
    suspend fun popularMovies(@Header(AUTHORIZATION_HEADER) authorization:String="$PREFIX_TOKEN ${BuildConfig.ACCESS_TOKEN}",
                       @Query(LANGUAGE_QUERY) language:String = Locale.getDefault().isO3Language,
                       @Query(PAGE_QUERY) page:Int = 1): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun ratedMovies(@Header(AUTHORIZATION_HEADER) authorization:String="$PREFIX_TOKEN ${BuildConfig.ACCESS_TOKEN}",
                              @Query(LANGUAGE_QUERY) language:String = Locale.getDefault().isO3Language,
                              @Query(PAGE_QUERY) page:Int = 1): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun upcomingMovies(@Header(AUTHORIZATION_HEADER) authorization:String="$PREFIX_TOKEN ${BuildConfig.ACCESS_TOKEN}",
                            @Query(LANGUAGE_QUERY) language:String = Locale.getDefault().isO3Language,
                            @Query(PAGE_QUERY) page:Int = 1): Response<MoviesResponse>


}
