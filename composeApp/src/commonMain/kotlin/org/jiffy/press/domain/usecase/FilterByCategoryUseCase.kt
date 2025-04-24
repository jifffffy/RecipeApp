package org.jiffy.press.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jiffy.press.domain.model.meal.Meal
import org.jiffy.press.domain.repository.MealsRepository

class FilterByCategoryUseCase (
    private val mealsRepository: MealsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
){
    suspend operator fun invoke(category: String): Result<List<Meal>> {
        return withContext(dispatcher) {
            mealsRepository.filter(query = "c", value = category)
        }
    }
}