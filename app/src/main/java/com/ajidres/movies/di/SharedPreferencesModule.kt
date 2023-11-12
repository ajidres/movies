package com.ajidres.movies.di

import android.content.Context
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.ajidres.movies.data.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(getDefaultSharedPreferences(context))
    }

}