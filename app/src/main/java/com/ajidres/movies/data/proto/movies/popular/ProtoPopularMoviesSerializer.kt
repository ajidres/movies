package com.ajidres.movies.data.proto.movies.popular

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.ajidres.movies.PopularMovies
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object ProtoPopularMoviesSerializer : Serializer<PopularMovies> {
    override val defaultValue: PopularMovies = PopularMovies.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): PopularMovies {
        try {
            return PopularMovies.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        } catch (e: java.io.IOException) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun writeTo(t: PopularMovies, output: OutputStream) = t.writeTo(output)
}

val Context.popularMoviesDataStore: DataStore<PopularMovies> by dataStore(
    fileName = "popular_movies_prefs.pb",
    serializer = ProtoPopularMoviesSerializer
)
