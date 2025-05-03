package org.jiffy.press.di

import org.jiffy.press.domain.usecase.FilterByAreaUseCase
import org.jiffy.press.domain.usecase.FilterByCategoryUseCase
import org.jiffy.press.domain.usecase.FilterByIngredientUseCase
import org.jiffy.press.domain.usecase.GettAllAreaUseCase
import org.jiffy.press.domain.usecase.GetAllCategoriesUseCase
import org.jiffy.press.domain.usecase.GettAllIngredientsUseCase
import org.jiffy.press.domain.usecase.GetMealsByTextUseCase
import org.jiffy.press.domain.usecase.LookupASingleRandomMealUseCase
import org.jiffy.press.domain.usecase.LookupFullMealDetailsByIdUseCase
import org.jiffy.press.domain.usecase.GetTopRecommendationsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        FilterByAreaUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }

    factory {
        FilterByCategoryUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
    factory {
        FilterByIngredientUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
    factory {
        GettAllAreaUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
    factory {
        GetAllCategoriesUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
    factory {
        GettAllIngredientsUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
    factory {
        GetMealsByTextUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
    factory {
        LookupASingleRandomMealUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
    factory {
        LookupFullMealDetailsByIdUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
    factory {
        GetTopRecommendationsUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
}