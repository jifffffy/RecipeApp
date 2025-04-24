package org.jiffy.press.data.source.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AreaResponse(val strArea: String)
@Serializable
data class AreasApiResponse (val meals: List<AreaResponse>)