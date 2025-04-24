package org.jiffy.press.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jiffy.press.domain.model.meal.Meal
import org.jiffy.press.domain.repository.MealsRepository

class FilterByIngredientUseCase (
    private val mealsRepository: MealsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
){
    suspend operator fun invoke(ingredients: List<String>): Result<List<Meal>> {
        return withContext(dispatcher) {
            mealsRepository.filter(query = "i", ingredients.joinToString(","))
        }
    }
}