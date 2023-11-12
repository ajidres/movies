package com.ajidres.movies.data.proto.people

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.ajidres.movies.PopularPeople
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object ProtoPeopleSerializer : Serializer<PopularPeople> {
    override val defaultValue: PopularPeople = PopularPeople.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): PopularPeople {
        try {
            return PopularPeople.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        } catch (e: java.io.IOException) {
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun writeTo(t: PopularPeople, output: OutputStream) = t.writeTo(output)
}

val Context.peopleDataStore: DataStore<PopularPeople> by dataStore(
    fileName = "popular_people_prefs.pb",
    serializer = ProtoPeopleSerializer
)