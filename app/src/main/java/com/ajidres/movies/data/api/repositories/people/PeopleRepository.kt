package com.ajidres.movies.data.api.repositories.people

import com.ajidres.movies.data.api.model.PeopleResponse
import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.domain.model.PeopleResponseUI
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {

    fun fetchPeople(page:Int): Flow<ResultEndpoints<PeopleResponseUI>>

}