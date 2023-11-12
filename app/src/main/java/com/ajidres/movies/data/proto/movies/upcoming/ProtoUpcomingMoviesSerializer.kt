package com.ajidres.movies.data.proto.movies.upcoming

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.ajidres.movies.UpcomingMovies
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object ProtoUpcomingMoviesSerializer : Serializer<UpcomingMovies> {
    override val defaultValue: UpcomingMovies = UpcomingMovies.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): UpcomingMovies {
        try {
            return UpcomingMovies.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        } catch (e: java.io.IOException) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun writeTo(t: UpcomingMovies, output: OutputStream) = t.writeTo(output)
}

val Context.upcomingMoviesDataStore: DataStore<UpcomingMovies> by dataStore(
    fileName = "upcoming_movies_prefs.pb",
    serializer = ProtoUpcomingMoviesSerializer
)