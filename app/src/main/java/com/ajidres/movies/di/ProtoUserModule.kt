package com.ajidres.movies.di

import android.content.Context
import com.ajidres.movies.data.proto.movies.popular.ProtoPopularMoviesRepository
import com.ajidres.movies.data.proto.movies.popular.ProtoPopularMoviesRepositoryImpl
import com.ajidres.movies.data.proto.movies.rated.ProtoRatedMoviesRepository
import com.ajidres.movies.data.proto.movies.rated.ProtoRatedMoviesRepositoryImpl
import com.ajidres.movies.data.proto.movies.upcoming.ProtoUpcomingMoviesRepository
import com.ajidres.movies.data.proto.movies.upcoming.ProtoUpcomingMoviesRepositoryImpl
import com.ajidres.movies.data.proto.people.ProtoPeopleRepository
import com.ajidres.movies.data.proto.people.ProtoPeopleRepositoryImpl
import com.ajidres.movies.data.proto.people.peopleDataStore
import com.ajidres.movies.data.proto.movies.popular.popularMoviesDataStore
import com.ajidres.movies.data.proto.movies.rated.ratedMoviesDataStore
import com.ajidres.movies.data.proto.movies.upcoming.upcomingMoviesDataStore
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProtoUserModule {

    @Provides
    @Reusable
    internal fun providesPeopleDataRepository(
        @ApplicationContext context: Context,
    ): ProtoPeopleRepository {
        return ProtoPeopleRepositoryImpl(
            context.peopleDataStore
        )
    }

    @Provides
    @Reusable
    internal fun providesPopularMoviesDataRepository(
        @ApplicationContext context: Context,
    ): ProtoPopularMoviesRepository {
        return ProtoPopularMoviesRepositoryImpl(
            context.popularMoviesDataStore
        )
    }

    @Provides
    @Reusable
    internal fun providesRatedMoviesDataRepository(
        @ApplicationContext context: Context,
    ): ProtoRatedMoviesRepository {
        return ProtoRatedMoviesRepositoryImpl(
            context.ratedMoviesDataStore
        )
    }

    @Provides
    @Reusable
    internal fun providesUpcomingMoviesDataRepository(
        @ApplicationContext context: Context,
    ): ProtoUpcomingMoviesRepository {
        return ProtoUpcomingMoviesRepositoryImpl(
            context.upcomingMoviesDataStore
        )
    }


}