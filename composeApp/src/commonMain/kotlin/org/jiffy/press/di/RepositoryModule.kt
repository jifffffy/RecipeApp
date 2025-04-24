package org.jiffy.press.di

import org.jiffy.press.data.repository.MealsRepositoryImpl
import org.jiffy.press.domain.repository.MealsRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<MealsRepository> {
        MealsRepositoryImpl(
            mealsEndpoint = get(),
            inMemoryCacheDataSource = get(),
            dispatcher = get(named("DefaultDispatcher"))
        )
    }
}