package com.ajidres.movies.data.proto.people

import com.ajidres.movies.domain.model.PeopleResponseUI
import kotlinx.coroutines.flow.Flow

interface ProtoPeopleRepository {


    suspend fun getPeopleData(): Flow<PeopleResponseUI>
    suspend fun savePeopleData(data: PeopleResponseUI)
    suspend fun clearAllData()




}