package org.jiffy.press.domain.repository

import org.jiffy.press.domain.model.category.Category
import org.jiffy.press.domain.model.ingredient.Ingredient
import org.jiffy.press.domain.model.meal.Meal

interface MealsRepository {
    suspend fun search(query: String, value: String ): Result<List<Meal>>
    suspend fun lookup(id: String): Result<Meal>
    suspend fun random(): Result<Meal>
    suspend fun getCategories(): Result<List<Category>>
    suspend fun listCategories(refresh: Boolean = false): Result<List<Category>>
    suspend fun listAreas(refresh: Boolean = false): Result<List<String>>
    suspend fun listIngredients(refresh: Boolean = false): Result<List<Ingredient>>
    suspend fun filter(query: String, value: String): Result<List<Meal>>
}