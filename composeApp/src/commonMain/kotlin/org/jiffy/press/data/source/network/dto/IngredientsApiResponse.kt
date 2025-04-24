package org.jiffy.press.data.source.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class IngredientsResponse(
    val idIngredient: String,
    val strIngredient: String,
    val strDescription: String?,
    val strType: String?
)

@Serializable
data class IngredientsApiResponse(
    val meals: List<IngredientsResponse>
)