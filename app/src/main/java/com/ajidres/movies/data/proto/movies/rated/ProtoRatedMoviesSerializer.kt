package com.ajidres.movies.data.proto.movies.rated

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.ajidres.movies.RatedMovies
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream


object ProtoRatedMoviesSerializer : Serializer<RatedMovies> {
    override val defaultValue: RatedMovies = RatedMovies.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): RatedMovies {
        try {
            return RatedMovies.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        } catch (e: java.io.IOException) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun writeTo(t: RatedMovies, output: OutputStream) = t.writeTo(output)
}

val Context.ratedMoviesDataStore: DataStore<RatedMovies> by dataStore(
    fileName = "rated_movies_prefs.pb",
    serializer = ProtoRatedMoviesSerializer
)
