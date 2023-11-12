package com.ajidres.movies.data.api.repositories.people

import com.ajidres.movies.data.api.EndPoints
import com.ajidres.movies.data.api.mapper.toPeopleResponseUI
import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.domain.model.PeopleResponseUI
import com.ajidres.movies.extentions.bodyOrException
import com.ajidres.movies.extentions.getErrorResponse
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class PeopleRepositoryImpl  @Inject constructor(private val apis: EndPoints) : PeopleRepository {

    override fun fetchPeople(page:Int): Flow<ResultEndpoints<PeopleResponseUI>> {
        return flow<ResultEndpoints<PeopleResponseUI>> {
            val response = apis.people(page = page).bodyOrException()
            emit(ResultEndpoints.Success(response.toPeopleResponseUI()))
        }.onStart {
            emit(ResultEndpoints.Loading(true))
        }.onCompletion {
            emit(ResultEndpoints.Loading(false))
        }.catch {
            emit(ResultEndpoints.Loading(false))
            emit(ResultEndpoints.Failure(errorMessage = it.getErrorResponse()))
        }.flowOn(Dispatchers.IO)
    }




}