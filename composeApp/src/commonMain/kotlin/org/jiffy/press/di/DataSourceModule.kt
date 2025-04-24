package org.jiffy.press.di

import org.jiffy.press.data.source.local.cache.InMemoryCacheDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<InMemoryCacheDataSource> {
        InMemoryCacheDataSource()
    }
}