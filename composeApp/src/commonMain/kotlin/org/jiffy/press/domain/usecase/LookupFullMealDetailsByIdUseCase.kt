package org.jiffy.press.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jiffy.press.domain.model.meal.Meal
import org.jiffy.press.domain.repository.MealsRepository

class LookupFullMealDetailsByIdUseCase(
    private val mealsRepository: MealsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(id: String): Result<Meal> {
        return withContext(dispatcher) {
            mealsRepository.lookup(id = id)
        }
    }
}