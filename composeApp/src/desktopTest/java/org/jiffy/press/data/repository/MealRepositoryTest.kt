package org.jiffy.press.data.repository

import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlinx.serialization.json.Json
import org.jiffy.press.data.samples.categoriesSampleData
import org.jiffy.press.data.samples.ingredientsSampleData
import org.jiffy.press.data.source.local.cache.InMemoryCacheDataSource
import org.jiffy.press.data.source.network.dto.CategoriesApiResponse
import org.jiffy.press.data.source.network.dto.FilterMealsApiResponse
import org.jiffy.press.data.source.network.dto.IngredientsApiResponse
import org.jiffy.press.data.source.network.dto.MealsApiResponse
import org.jiffy.press.data.source.network.restapi.MealsEndpoint
import org.jiffy.press.domain.model.category.Category
import org.jiffy.press.samples.FilterAreaSampleData
import org.jiffy.press.samples.FilterCategorySampleData
import org.jiffy.press.samples.ListAllIngredientsSampleData
import org.jiffy.press.samples.ListAllMealCategoriesSampleData
import org.jiffy.press.samples.SearchMealByNameSampleData
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.*
import kotlin.time.Duration.Companion.seconds

class MealsRepositoryImplTest {
    private val testDispatcher = StandardTestDispatcher()
    private val mealsEndpoint = mockk<MealsEndpoint>()
    private val cacheDataSource = mockk<InMemoryCacheDataSource>()
    private val repository = MealsRepositoryImpl(
        mealsEndpoint,
        cacheDataSource,
        testDispatcher
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    // region Search Tests
    @Test
    fun `search should return meals on successful response`() = runTest {
        // Mock
        coEvery { mealsEndpoint.search("s", "Arrabiata") } returns Json.decodeFromString(
            MealsApiResponse.serializer(),
            SearchMealByNameSampleData.json
        )

        // Test
        val result = repository.search("s", "Arrabiata")

        // Verify
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        assertEquals("Spicy Arrabiata Penne", result.getOrNull()?.first()?.name)

    }

    @Test
    fun `search should handle empty response gracefully`() = runTest {
        coEvery { mealsEndpoint.search(any(), any()) } returns null

        val result = repository.search("s", "invalid")

        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()!!.isEmpty())
    }
    // endregion

    // region Cache Tests
    @Test
    fun `listCategories should use cache when valid and refresh false`() = runTest {
        // Mock cache
        val cachedResult: List<Category> = categoriesSampleData
        every { cacheDataSource.getCategories() } returns cachedResult

        // Test
        val result = repository.listCategories(refresh = false)

        // Verify
        assertEquals(cachedResult.size, result.getOrNull()?.size)
        coVerify(exactly = 0) { mealsEndpoint.listCategories()?.categories?.size }
    }

    @Test
    fun `listCategories should fetch fresh data when refresh requested`() =
        runTest(timeout = 5.seconds) {
            // Mock fresh data
            val cachedResult = categoriesSampleData
            every { cacheDataSource.getCategories() } returns cachedResult
            every { cacheDataSource.cacheCategories(any()) } returns Unit
            coEvery { mealsEndpoint.listCategories() } returns Json.decodeFromString(
                CategoriesApiResponse.serializer(),
                ListAllMealCategoriesSampleData.json
            )

            // Test
            val result = repository.listCategories(refresh = true)

            // Verify
            assertTrue(result.isSuccess)
            assertEquals("Italian Cuisine", cachedResult.first().name)
            assertEquals("Beef", result.getOrNull()?.first()?.name)
        }

    @Test
    fun `listIngredients should fetch fresh data when refresh requested`() =
        runTest(timeout = 5.seconds) {
            // Mock fresh data
            val cachedResult = ingredientsSampleData
            every { cacheDataSource.getIngredients() } returns cachedResult
            every { cacheDataSource.cacheIngredients(any()) } returns Unit
            coEvery { mealsEndpoint.listIngredients() } returns Json.decodeFromString(
                IngredientsApiResponse.serializer(),
                ListAllIngredientsSampleData.json
            )

            // Test
            val result = repository.listIngredients(refresh = true)

            // Verify
            assertTrue(result.isSuccess)
            assertEquals("Tomato", cachedResult.first().name)
            assertEquals("Chicken", result.getOrNull()?.first()?.name)
        }

    // endregion

    // region Error Handling Tests
    @Test
    fun `should propagate cancellation exceptions`() = runTest {
        coEvery {
            mealsEndpoint.search(
                any(),
                any()
            )
        } throws CancellationException("Test cancellation")

        assertFailsWith<CancellationException> {
            repository.search("s", "test")
        }
    }

    @Test
    fun `should wrap general exceptions in Result`() = runTest {
        coEvery { mealsEndpoint.search(any(), any()) } throws IllegalStateException("API Error")

        val result = repository.search("s", "error")

        assertTrue(result.isFailure)
        assertIs<IllegalStateException>(result.exceptionOrNull())
    }
    // endregion

    // region Filter Tests
    @Test
    fun `filter by category should convert response correctly`() = runTest {

        coEvery { mealsEndpoint.filter("c", "Seafood") } returns Json.decodeFromString(
            FilterMealsApiResponse.serializer(),
            FilterCategorySampleData.json
        )


        val result = repository.filter("c", "Seafood")

        assertTrue(result.isSuccess)
        assertEquals("Baked salmon with fennel & tomatoes", result.getOrNull()?.first()?.name)
    }

    @Test
    fun `filter by area should convert response correctly`() = runTest {
        coEvery { mealsEndpoint.filter("a", "BeaverTails") } returns Json.decodeFromString(
            FilterMealsApiResponse.serializer(),
            FilterAreaSampleData.json
        )

        val result = repository.filter("a", "BeaverTails")

        assertTrue(result.isSuccess)
        assertEquals("BeaverTails", result.getOrNull()?.first()?.name)
    }
    // endregion


}