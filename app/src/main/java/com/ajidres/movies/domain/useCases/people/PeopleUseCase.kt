package com.ajidres.movies.domain.useCases.people

import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.domain.model.PeopleResponseUI
import kotlinx.coroutines.flow.Flow

interface PeopleUseCase {

    fun invokeFetchPeople(page:Int): Flow<ResultEndpoints<PeopleResponseUI>>
    suspend fun savePeopleData(data: PeopleResponseUI)
    suspend fun fetchPeopleData():Flow<PeopleResponseUI>

}