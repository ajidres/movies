package com.ajidres.movies.data.proto.people

import androidx.datastore.core.DataStore
import com.ajidres.movies.PopularPeople
import com.ajidres.movies.data.proto.mapper.toPeopleResponseUI
import com.ajidres.movies.data.proto.mapper.toPopularPeopleList
import com.ajidres.movies.domain.model.PeopleResponseUI
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProtoPeopleRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<PopularPeople>
) : ProtoPeopleRepository {


    override suspend fun getPeopleData(): Flow<PeopleResponseUI> {
        return dataStore.data.map { it.toPeopleResponseUI() }
    }

    override suspend fun savePeopleData(data: PeopleResponseUI) {

        dataStore.updateData { peoplePreferences: PopularPeople ->
            with(data){
                peoplePreferences.toBuilder().clear()
                    .setPage(page)
                    .setTotalPages(totalPages)
                    .addAllPeopleList(data.results.toPopularPeopleList())
                    .build()
            }

        }
    }

    override suspend fun clearAllData() {
        dataStore.updateData { surveysPreferences: PopularPeople ->
            surveysPreferences.toBuilder().clear().build()
        }
    }
}