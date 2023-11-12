package com.ajidres.movies.data.api.mapper

import com.ajidres.movies.data.api.model.PeopleResponse
import com.ajidres.movies.domain.model.PeopleResponseUI
import com.ajidres.movies.domain.model.ResultUI


fun PeopleResponse.toPeopleResponseUI(): PeopleResponseUI =
    with(this) {
        return PeopleResponseUI(
            page = page,
            results = results.sortedByDescending { it.popularity }.map { it ->
                ResultUI(
                    id = it.id,
                    knownFor = it.knownFor.map { kf ->
                        kf.originalTitle ?: kf.originalName
                    }.joinToString { name-> name },
                    name = it.name,
                    profilePath = "https://image.tmdb.org/t/p/original${it.profilePath}",
                    rate = it.popularity.toInt()
                )
            },
            totalPages = totalPages
        )
    }
