package org.jiffy.press.data.source.network.restapi

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.parameters
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.jiffy.press.data.source.network.dto.*
import org.jiffy.press.domain.exceptions.HttpException

class MealsEndpoint(
    baseUrl: String,
    private val httpClient: HttpClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val endpointUrl = "$baseUrl/1"

    suspend fun search(query: String, value: String): MealsApiResponse? =
        safeRequest {
            httpClient.get("$endpointUrl/search.php") {
                url {
                    parameters.append(query, value)
                }
            }.parseResponse()
        }

    suspend fun lookup(idMeal: String): MealsApiResponse? =
        safeRequest {
            httpClient.get("$endpointUrl/lookup.php") {
                url {
                    parameters.append("i", idMeal)
                }
            }.parseResponse()
        }

    suspend fun random(): MealsApiResponse? =
        safeRequest {
            httpClient.get("$endpointUrl/randomselection.php").parseResponse()
        }

    suspend fun categories(): CategoriesApiResponse? =
        safeRequest {
            httpClient.get("$endpointUrl/categories.php").parseResponse()
        }

    suspend fun filter(query: String, value: String): FilterMealsApiResponse? =
        safeRequest {
            httpClient.get("$endpointUrl/filter.php") {
                url {
                    parameters.append(query, value)
                }
            }.parseResponse<FilterMealsApiResponse>()
        }

    suspend fun listCategories(): CategoriesApiResponse? =
        safeRequest {
            httpClient.get("$endpointUrl/list.php") {
                url {
                    parameters.append("c", "list")
                }
            }.parseResponse()
        }

    suspend fun listAreas(): AreasApiResponse? =
        safeRequest {
            httpClient.get("$endpointUrl/list.php") {
                url {
                    parameters.append("a", "list")
                }
            }.parseResponse()
        }

    suspend fun listIngredients(): IngredientsApiResponse? =
        safeRequest {
            httpClient.get("$endpointUrl/list.php") {
                url {
                    parameters.append("i", "list")
                }
            }.parseResponse()
        }


    private suspend inline fun <reified T> safeRequest(crossinline block: suspend () -> T): T? {
        return try {
            withContext(dispatcher) { block() }
        } catch (e: HttpException) {
            null
        }
    }

    private suspend inline fun <reified T> io.ktor.client.statement.HttpResponse.parseResponse(): T {
        return when (status) {
            HttpStatusCode.OK -> body()
            HttpStatusCode.NotFound -> null
            else -> throw HttpException(status.value)
        } ?: throw HttpException(status.value)
    }

}