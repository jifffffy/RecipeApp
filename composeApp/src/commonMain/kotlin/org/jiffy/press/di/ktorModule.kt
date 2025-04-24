package org.jiffy.press.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.jiffy.press.data.source.network.restapi.MealsEndpoint
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BASE_URL = "https://www.themealdb.com/api/json/v1"

@OptIn(ExperimentalSerializationApi::class)
val ktorModule = module {
    single {
        HttpClient(engine = get()) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                        allowTrailingComma = true
                    }
                )
            }
        }
    }

    factory {
        MealsEndpoint(
            baseUrl = BASE_URL,
            httpClient = get(),
            dispatcher = get(named("IoDispatcher"))
        )
    }
}