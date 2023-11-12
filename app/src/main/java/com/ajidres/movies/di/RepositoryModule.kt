package com.ajidres.movies.di

import com.ajidres.movies.data.api.EndPoints
import com.ajidres.movies.data.api.repositories.movies.MoviesRepository
import com.ajidres.movies.data.api.repositories.movies.MoviesRepositoryImpl
import com.ajidres.movies.data.api.repositories.people.PeopleRepository
import com.ajidres.movies.data.api.repositories.people.PeopleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePeopleRepository(apis: EndPoints): PeopleRepository {
            return PeopleRepositoryImpl(apis)
    }

    @Provides
    fun provideMoviesRepository(apis: EndPoints): MoviesRepository {
        return MoviesRepositoryImpl(apis)
    }
}