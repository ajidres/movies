package com.ajidres.movies.di

import com.ajidres.movies.BuildConfig
import com.ajidres.movies.BuildConfig.*
import com.ajidres.movies.data.api.EndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TIME_CONNECTION = 1L

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideApi(): EndPoints {

        val interceptor = HttpLoggingInterceptor()

//        if (DEBUG) {
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
//        }


        val httpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(TIME_CONNECTION, TimeUnit.MINUTES)
            .writeTimeout(TIME_CONNECTION, TimeUnit.MINUTES)
            .connectTimeout(TIME_CONNECTION, TimeUnit.MINUTES)
            .addInterceptor(interceptor).build()


        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ENDPOINT_URL)
            .client(httpClient)
            .build()
            .create(EndPoints::class.java)
    }
}