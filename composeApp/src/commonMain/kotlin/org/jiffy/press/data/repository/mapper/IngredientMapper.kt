package org.jiffy.press.data.repository.mapper

import org.jiffy.press.data.source.network.dto.IngredientsApiResponse
import org.jiffy.press.domain.model.ingredient.Ingredient

fun IngredientsApiResponse.mapToDomain(): List<Ingredient> {
    return meals.map {
        Ingredient(
            id = it.idIngredient,
            name = it.strIngredient,
            description = it.strDescription,
            type = it.strType
        )
    }
}