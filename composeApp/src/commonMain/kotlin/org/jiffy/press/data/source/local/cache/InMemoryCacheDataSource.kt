package org.jiffy.press.data.source.local.cache


import co.touchlab.kermit.Logger
import org.jiffy.press.domain.model.category.Category
import org.jiffy.press.domain.model.ingredient.Ingredient
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


class InMemoryCacheDataSource {
    private var categoriesCache: Pair<Long, List<Category>>? = null
    private var areasCache: Pair<Long, List<String>>? = null
    private var ingredientsCache: Pair<Long, List<Ingredient>>? = null

    companion object {
        const val CACHE_DURATION = 5 * 60 * 1000 // 5分钟
    }

    fun cacheCategories(categories: List<Category>) {
        Logger.i { "Categories cached: ${categories.size}"  }
        categoriesCache = Pair(currentTimeMillis(), categories)
    }

    fun cacheAreas(areas: List<String>) {
        areasCache = Pair(currentTimeMillis(), areas)
    }

    fun cacheIngredients(ingredients: List<Ingredient>) {
        ingredientsCache = Pair(currentTimeMillis(), ingredients)
    }

    fun getIngredients(): List<Ingredient>? {
        return ingredientsCache?.let {
            if (currentTimeMillis() - it.first < CACHE_DURATION) {
                it.second
            } else null
        }
    }

    fun getAreas(): List<String>? {
        return areasCache?.let {
            if (currentTimeMillis() - it.first < CACHE_DURATION) {
                it.second
            } else null
        }
    }

    fun getCategories(): List<Category>? {
        return categoriesCache?.let {
            if (currentTimeMillis() - it.first < CACHE_DURATION) {
                it.second
            } else null
        }
    }

    @OptIn(ExperimentalTime::class)
    fun currentTimeMillis(): Long {
        return Clock.System.now().toEpochMilliseconds()
    }

}