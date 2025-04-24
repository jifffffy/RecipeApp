package org.jiffy.press.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jiffy.press.domain.model.meal.Meal
import org.jiffy.press.domain.repository.MealsRepository

class SearchMealByNameUseCase(
    private val mealsRepository: MealsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
)  {
    suspend operator fun invoke(name: String): Result<List<Meal>> {
        return withContext(dispatcher) {
            mealsRepository.search(query = "s", value = name)
        }
    }
}
