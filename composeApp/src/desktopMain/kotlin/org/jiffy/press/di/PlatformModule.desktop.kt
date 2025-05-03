package org.jiffy.press.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module


val platformModule = module {
    single<HttpClientEngine> { CIO.create() }
}
