package org.jiffy.press.data.repository.mapper

import org.jiffy.press.data.source.network.dto.AreasApiResponse

fun AreasApiResponse.mapToDomain(): List<String> {
    return meals.map { it.strArea }
}