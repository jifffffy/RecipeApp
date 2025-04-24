package org.jiffy.press.data.source.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class FilterMealsResponse(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
)

@Serializable
data class FilterMealsApiResponse(
    val meals: List<FilterMealsResponse>
)
