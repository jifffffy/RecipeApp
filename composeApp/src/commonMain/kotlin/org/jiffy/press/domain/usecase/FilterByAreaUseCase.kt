package org.jiffy.press.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jiffy.press.domain.model.meal.Meal
import org.jiffy.press.domain.repository.MealsRepository

class FilterByAreaUseCase(
    private val mealsRepository: MealsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(area: String): Result<List<Meal>> {
        return withContext(dispatcher) {
            mealsRepository.filter("a", area)
        }
    }
}