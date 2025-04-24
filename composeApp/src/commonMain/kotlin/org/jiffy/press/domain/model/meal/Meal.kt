package org.jiffy.press.domain.model.meal

import org.jiffy.press.domain.model.ingredient.Ingredient

data class Meal(
    val id: String,
    val name: String,
    val alternateName: String? = "",
    val category: String? = "",
    val area: String? = "",
    val instructions: String? = "",
    val thumbnailUrl: String? = "",
    val tags: List<String>? = null,
    val youtubeUrl: String? = "",
    val ingredients: List<Ingredient>? = null,
    val source: String? = "",
    val imageSource: String? = "",
    val creativeCommonsConfirmed: Boolean? = false,
    val dateModified: String? = ""
)