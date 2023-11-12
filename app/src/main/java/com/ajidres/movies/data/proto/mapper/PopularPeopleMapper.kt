package com.ajidres.movies.data.proto.mapper

import com.ajidres.movies.PopularPeople
import com.ajidres.movies.PopularPeopleList
import com.ajidres.movies.domain.model.PeopleResponseUI
import com.ajidres.movies.domain.model.ResultUI

fun PopularPeople.toPeopleResponseUI():PeopleResponseUI{
    with(this){
        return PeopleResponseUI(
            page=page,
            results = peopleListList.map { ResultUI(
                id= it.id,
                knownFor= it.knownFor,
                name= it.name,
                profilePath= it.profilePath,
                rate = it.rate
            ) },
            totalPages=totalPages
        )
    }
}

fun List<ResultUI>.toPopularPeopleList():List<PopularPeopleList>{
    with(this){
        return map {
            PopularPeopleList.newBuilder()
                .setId(it.id)
                .setKnownFor(it.toMoviesList())
                .setName(it.name)
                .setProfilePath(it.profilePath)
                .build()
        }.toList()
    }
}