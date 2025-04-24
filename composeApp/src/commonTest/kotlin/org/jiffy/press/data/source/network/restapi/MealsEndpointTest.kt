package org.jiffy.press.data.source.network.restapi

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.jiffy.press.samples.*
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNull

import kotlin.test.Test

class MealsEndpointTest {
    private companion object {
        const val FAKE_BASE_URL = "https://fakebaseurl.com"
        val JSON_HEADERS = headersOf(HttpHeaders.ContentType, "application/json")

        val jsonConfig = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            allowTrailingComma = true
        }
    }


    private fun createMockClient(
        payload: String,
        status: HttpStatusCode = HttpStatusCode.OK
    ): HttpClient {
        val engine = MockEngine { _ ->
            respond(
                content = ByteReadChannel(payload),
                status = status,
                headers = JSON_HEADERS
            )
        }

        return HttpClient(engine) {
            install(ContentNegotiation) { json(jsonConfig) }
        }
    }

    private fun createEndpoint(payload: String) = MealsEndpoint(
        baseUrl = FAKE_BASE_URL,
        httpClient = createMockClient(payload)
    )


    @Test
    fun `search meal by name should return matching meal`() = runTest {
        // Given
        val endpoint = createEndpoint(SearchMealByNameSampleData.json)

        // When
        val result = endpoint.search(query = "s", value = "Arrabiata")

        // Then
        val meal = result?.meals?.first()
        assertEquals(expected = 1, actual = result?.meals?.size)
        assertContains(meal?.strMeal.orEmpty(), "Arrabiata")
    }

    @Test
    fun `search by first letter should return correct number of meals`() = runTest {
        val endpoint = createEndpoint(ListAllMealsByFirstLetterSampleData.json)

        val result = endpoint.search(query = "f", value = "a")

        assertEquals(expected = 4, actual = result?.meals?.size)
    }

    @Test
    fun `lookup by id should return meal with correct id`() = runTest {
        val endpoint = createEndpoint(LookupFullMealDetailsByIdSampleData.json)

        val result = endpoint.lookup(idMeal = "52772")

        assertEquals(expected = "52772", actual = result?.meals?.first()?.idMeal)
    }

    @Test
    fun `random meal should return single result`() = runTest {
        val endpoint = createEndpoint(LookupASingleRandomMealSampleData.json)

        val result = endpoint.random()

        assertEquals(expected = 1, actual = result?.meals?.size)
    }

    @Test
    fun `list categories should return all available categories`() = runTest {
        val endpoint = createEndpoint(ListAllMealCategoriesSampleData.json)

        val result = endpoint.categories()

        assertEquals(expected = 14, actual = result?.categories?.size)
    }

    @Test
    fun `list ingredients should return valid ingredients response`() = runTest {
        val endpoint = createEndpoint(ListAllIngredientsSampleData.json)

        val result = endpoint.listIngredients()


        assertEquals(
            expected = 575,
            actual = result?.meals?.size
        )
    }

    @Test
    fun `search should return null when server returns 404`() = runTest {
        val client = createMockClient(
            payload = "",
            status = HttpStatusCode.NotFound
        )
        val endpoint = MealsEndpoint(FAKE_BASE_URL, client)

        val result = endpoint.search("s", "test")

        assertNull(result)
    }

    @Test
    fun `filter meals by ingredient should return matching meals`() = runTest {
        // Given
        val endpoint = createEndpoint(FilterIngredientSampleData.json)

        // When
        val result = endpoint.filter(query = "i", value = "chicken_breast")

        // Then
        assertEquals(expected = 9, actual = result?.meals?.size)
    }

    @Test
    fun `filter meals by category should return matching meals`() = runTest {
        // Given
        val endpoint = createEndpoint(FilterCategorySampleData.json)

        // When
        val result = endpoint.filter(query = "c", value = "Seafood")

        // Then
        assertEquals(expected = 29, actual = result?.meals?.size)
    }

    @Test
    fun `filter meals by area should return matching meals`() = runTest {
        // Given
        val endpoint = createEndpoint(FilterIngredientSampleData.json)

        // When
        val result = endpoint.filter(query = "a", value = "chicken_breast")

        // Then
        assertEquals(expected = 9, actual = result?.meals?.size)
    }

}

