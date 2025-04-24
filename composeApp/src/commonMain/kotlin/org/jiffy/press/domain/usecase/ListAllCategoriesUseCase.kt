package org.jiffy.press.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jiffy.press.domain.model.category.Category
import org.jiffy.press.domain.repository.MealsRepository

class ListAllCategoriesUseCase(
    private val mealsRepository: MealsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend operator fun invoke(): Result<List<Category>> {
        return withContext(dispatcher) {
            mealsRepository.getCategories()
        }
    }
}