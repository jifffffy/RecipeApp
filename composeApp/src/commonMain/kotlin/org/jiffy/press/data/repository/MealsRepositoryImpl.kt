package org.jiffy.press.data.repository

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jiffy.press.data.repository.mapper.mapToDomain
import org.jiffy.press.data.source.local.cache.InMemoryCacheDataSource
import org.jiffy.press.data.source.network.restapi.MealsEndpoint
import org.jiffy.press.domain.exceptions.except
import org.jiffy.press.domain.model.category.Category
import org.jiffy.press.domain.model.ingredient.Ingredient
import org.jiffy.press.domain.model.meal.Meal
import org.jiffy.press.domain.repository.MealsRepository
import kotlin.coroutines.cancellation.CancellationException


class MealsRepositoryImpl(
    private val mealsEndpoint: MealsEndpoint,
    private val inMemoryCacheDataSource: InMemoryCacheDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : MealsRepository {

    override suspend fun search(query: String, value: String): Result<List<Meal>> {
        return withContext(dispatcher) {
            runCatching {
                val mealList = mutableListOf<Meal>()
                val response = mealsEndpoint.search(query, value);
                response?.let {
                    mealList.addAll(response.mapToDomain())
                }
                mealList
            }.except<CancellationException, _>()
        }
    }

    override suspend fun lookup(id: String): Result<Meal> {
        return withContext(dispatcher) {
            runCatching {
                val response = mealsEndpoint.lookup(id);
                response?.mapToDomain()?.first()
                    ?: throw IllegalArgumentException("Invalid meal with id $id")
            }.except<CancellationException, _>()
        }
    }

    override suspend fun random(): Result<Meal> {
        return withContext(dispatcher) {
            runCatching {
                val response = mealsEndpoint.random();
                response?.mapToDomain()?.first()
                    ?: throw IllegalArgumentException("Random meal not found")
            }.except<CancellationException, _>()
        }
    }

    override suspend fun getCategories(): Result<List<Category>> {
        return withContext(dispatcher) {
            runCatching {
                val response = mealsEndpoint.categories();
                response?.mapToDomain() ?: throw IllegalArgumentException("Categories not found")
            }.except<CancellationException, _>()
        }
    }


    override suspend fun listCategories(refresh: Boolean): Result<List<Category>> {
        if (!refresh) {
            inMemoryCacheDataSource.getCategories()?.let {
                return Result.success(it)
            }
        }
        return withContext(dispatcher) {
            runCatching {
                val response = mealsEndpoint.listCategories()
                val categories = response?.mapToDomain()
                    ?: throw IllegalArgumentException("Categories not found")
                inMemoryCacheDataSource.cacheCategories(categories)
                categories
            }.except<CancellationException, _>()
        }
    }

    override suspend fun listAreas(refresh: Boolean): Result<List<String>> {
        if (!refresh) {
            inMemoryCacheDataSource.getAreas()?.let {
                return Result.success(it)
            }
        }
        return withContext(dispatcher) {
            runCatching {
                val response = mealsEndpoint.listAreas()
                val areas = response?.mapToDomain()
                    ?: throw IllegalArgumentException("Areas not found")
                inMemoryCacheDataSource.cacheAreas(areas)
                areas
            }.except<CancellationException, _>()
        }
    }

    override suspend fun listIngredients(refresh: Boolean): Result<List<Ingredient>> {
        if (!refresh) {
            inMemoryCacheDataSource.getIngredients()?.let {
                return Result.success(it)
            }
        }
        return withContext(dispatcher) {
            runCatching {
                val response = mealsEndpoint.listIngredients()
                val ingredients = response?.mapToDomain()
                    ?: throw IllegalArgumentException("Ingredients not found")
                inMemoryCacheDataSource.cacheIngredients(ingredients)
                ingredients
            }.except<CancellationException, _>()
        }
    }

    override suspend fun filter(query: String, value: String): Result<List<Meal>> {
        return withContext(dispatcher) {
            runCatching {
                val response = mealsEndpoint.filter(query, value)
                response?.mapToDomain() ?: throw IllegalArgumentException("Invalid query")
            }.except<CancellationException, _>()
        }
    }

}