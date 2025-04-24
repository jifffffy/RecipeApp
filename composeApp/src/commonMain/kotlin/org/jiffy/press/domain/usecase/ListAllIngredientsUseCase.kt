package org.jiffy.press.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jiffy.press.domain.model.ingredient.Ingredient
import org.jiffy.press.domain.repository.MealsRepository

class ListAllIngredientsUseCase(
    private val mealsRepository: MealsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(): Result<List<Ingredient>> {
        return withContext(dispatcher) {
            mealsRepository.listIngredients()
        }
    }
}