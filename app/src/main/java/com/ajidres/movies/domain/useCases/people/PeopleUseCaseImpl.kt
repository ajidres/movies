package com.ajidres.movies.domain.useCases.people

import com.ajidres.movies.data.api.model.ResultEndpoints
import com.ajidres.movies.data.api.repositories.people.PeopleRepository
import com.ajidres.movies.data.proto.people.ProtoPeopleRepository
import com.ajidres.movies.domain.model.PeopleResponseUI
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PeopleUseCaseImpl @Inject constructor(private val apiRepository: PeopleRepository,
                                            private val dbRepository: ProtoPeopleRepository
): PeopleUseCase {

    override fun invokeFetchPeople(page: Int): Flow<ResultEndpoints<PeopleResponseUI>> = apiRepository.fetchPeople(page)
    override suspend fun savePeopleData(data: PeopleResponseUI) = dbRepository.savePeopleData(data)
    override suspend fun fetchPeopleData()  =  dbRepository.getPeopleData()

}