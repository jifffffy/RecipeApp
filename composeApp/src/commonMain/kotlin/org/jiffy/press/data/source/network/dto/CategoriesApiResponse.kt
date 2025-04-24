package org.jiffy.press.data.source.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponse(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

@Serializable
data class CategoriesApiResponse(
    val categories: List<CategoriesResponse>
)