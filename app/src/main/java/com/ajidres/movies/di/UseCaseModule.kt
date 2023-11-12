package com.ajidres.movies.di

import com.ajidres.movies.data.api.repositories.movies.MoviesRepository
import com.ajidres.movies.data.api.repositories.people.PeopleRepository
import com.ajidres.movies.data.proto.movies.popular.ProtoPopularMoviesRepository
import com.ajidres.movies.data.proto.movies.rated.ProtoRatedMoviesRepository
import com.ajidres.movies.data.proto.movies.upcoming.ProtoUpcomingMoviesRepository
import com.ajidres.movies.data.proto.people.ProtoPeopleRepository
import com.ajidres.movies.domain.useCases.movies.MoviesUseCase
import com.ajidres.movies.domain.useCases.movies.MoviesUseCaseImpl
import com.ajidres.movies.domain.useCases.people.PeopleUseCase
import com.ajidres.movies.domain.useCases.people.PeopleUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun providePeopleUseCase(
        apiRepository: PeopleRepository,
        dbRepository: ProtoPeopleRepository
    ): PeopleUseCase {
        return PeopleUseCaseImpl(apiRepository, dbRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideMoviesUseCase(
        apiRepository: MoviesRepository,
        dbPopularRepository: ProtoPopularMoviesRepository,
        dbRatedRepository: ProtoRatedMoviesRepository,
        dbUpcomingRepository: ProtoUpcomingMoviesRepository
    ): MoviesUseCase {
        return MoviesUseCaseImpl(apiRepository, dbPopularRepository, dbRatedRepository, dbUpcomingRepository)
    }




}