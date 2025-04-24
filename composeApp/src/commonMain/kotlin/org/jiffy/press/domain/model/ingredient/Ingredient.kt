package org.jiffy.press.domain.model.ingredient

data class Ingredient(
    val id: String,
    val name: String,
    val description: String? = null,
    val type: String? = null,
    val measure: String? = null,
)
