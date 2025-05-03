package org.jiffy.press.di


import org.jiffy.press.ui.destinations.home.HomeViewModel
import org.jiffy.press.ui.destinations.search.SearchViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    factory {
        HomeViewModel(
            getTopRecommendationsUseCase = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }

    factory {
        SearchViewModel(
            getMealsByTextUseCase = get(),
            dispatcher = get(named("DefaultDispatcher")),
        )
    }
}