package org.jiffy.press.di

import org.jiffy.press.domain.usecase.FilterByAreaUseCase
import org.jiffy.press.domain.usecase.FilterByCategoryUseCase
import org.jiffy.press.domain.usecase.FilterByIngredientUseCase
import org.jiffy.press.domain.usecase.ListAllAreaUseCase
import org.jiffy.press.domain.usecase.ListAllCategoriesUseCase
import org.jiffy.press.domain.usecase.ListAllIngredientsUseCase
import org.jiffy.press.domain.usecase.ListAllMealsByFirstLetterUseCase
import org.jiffy.press.domain.usecase.LookupASingleRandomMealUseCase
import org.jiffy.press.domain.usecase.LookupFullMealDetailsByIdUseCase
import org.jiffy.press.domain.usecase.SearchMealByNameUseCase
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
        ListAllAreaUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
    factory {
        ListAllCategoriesUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
    factory {
        ListAllIngredientsUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
    factory {
        ListAllMealsByFirstLetterUseCase(
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
        SearchMealByNameUseCase(
            mealsRepository = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
}