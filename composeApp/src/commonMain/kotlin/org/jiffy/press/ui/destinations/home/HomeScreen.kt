package org.jiffy.press.ui.destinations.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jiffy.press.domain.model.meal.Meal
import org.jiffy.press.ui.components.CommonPreviewSetup
import org.jiffy.press.ui.components.ErrorContent
import org.jiffy.press.ui.components.HorizontalScrollbarMultiplatform
import org.jiffy.press.ui.components.Loader
import org.jiffy.press.ui.components.MealCard
import org.jiffy.press.ui.composehelper.conditionalBlur


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUIState,
    uiEvent: HomeUIEvent
) {
    val meals = uiState.meals

    LaunchedEffect(true) {
        uiEvent.onRefresh()
    }

    Scaffold(
        topBar = {
            TopBar(uiEvent.onNavigateToSearch)
        }
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                Loader()
            }

            uiState.error !== null -> {
                ErrorContent()
            }

            meals != null -> {
                HomeScreenContent(
                    uiState = uiState,
                    innerPadding = innerPadding,
                    meals = meals,
                    navigateToDetail = { id -> uiEvent.onNavigateToDetail(id) }
                )
            }
        }

    }
}


@Composable
fun HomeScreenContent(
    uiState: HomeUIState,
    innerPadding: PaddingValues,
    meals: List<Meal>,
    navigateToDetail: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            TopMeals(
                uiState = uiState,
                title = "Top Recommendations",
                navigateToDetail = navigateToDetail,
                meals = meals.reversed()
            )
        }

        mealsOfTheWeek(
            title = "Recipes Of the Week", meals = meals, navigateToDetail = navigateToDetail
        )
    }
}

private fun LazyGridScope.mealsOfTheWeek(
    title: String,
    meals: List<Meal>,
    navigateToDetail: (String) -> Unit
) {
    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
    }

    itemsIndexed(meals, key = { _, it -> it.id }) { index, meal ->

        val cardPaddingStart = if (index % 2 == 0) 16.dp else 0.dp
        val cardPaddingEnd = if (index % 2 == 0) 0.dp else 16.dp

        val imageModifier =
            Modifier.fillMaxWidth().height(130.dp).clip(RoundedCornerShape(16.dp))
        MealCard(
            meal,
            modifier = Modifier.padding(start = cardPaddingStart, end = cardPaddingEnd),
            imageModifier = imageModifier.clickable {
                navigateToDetail(meal.id)
            }
        )
    }
}

@Composable
fun TopMeals(
    uiState: HomeUIState,
    title: String,
    navigateToDetail: (String) -> Unit,
    meals: List<Meal>
) {
    val lazyListState = rememberLazyListState()
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )

        HorizontalScrollbarMultiplatform(
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.meals?.isNotEmpty() ?: false,
            lazyListState = lazyListState
        ) {
            LazyRow(
                modifier = Modifier.conditionalBlur(enabled = uiState.isLoading),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                state = lazyListState,
            ) {
                items(meals, key = { it.id }) { meal ->
                    val imageModifier =
                        Modifier.width(120.dp).height(140.dp).clip(RoundedCornerShape(16.dp))
                    MealCard(
                        meal,
                        modifier = Modifier.width(110.dp),
                        imageModifier = imageModifier.clickable {
                            navigateToDetail(meal.id)
                        }
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun TopBarPreview() {
    CommonPreviewSetup {
        TopBar { }
    }
}


@Composable
fun TopBar(navigateToSearch: () -> Unit) {
    Column(
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            text = "Hi Alan!",
            color = MaterialTheme.colorScheme.primaryContainer,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Got a tasty dish in mind?",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        SearchBar(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp).height(45.dp)
                .background(MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                )
                .padding(horizontal = 16.dp)
                .clickable {
                    navigateToSearch()
                },
        )

    }
}


@Composable
private fun SearchBar(
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Search any recipes",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.7f
                )
            )
        }
    }
}



