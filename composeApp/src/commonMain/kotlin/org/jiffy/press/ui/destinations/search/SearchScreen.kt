package org.jiffy.press.ui.destinations.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import org.jiffy.press.ui.components.Loader
import org.jiffy.press.ui.components.MealCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiEvent: SearchUIEvent,
    uiState: SearchUIState,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    Text(
                        text = "Search Recipes"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = uiEvent.onNavigateBack
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SearchScreenContent(
                 uiState, uiEvent, innerPadding
            )
        }
    }

}


@Composable
fun SearchScreenContent(
    uiState: SearchUIState,
    uiEvent: SearchUIEvent,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        OutlinedTextField(
            shape = MaterialTheme.shapes.medium,
            value = uiState.searchText,
            onValueChange = {
                uiEvent.onSearchTextChanged(it)
            },
            placeholder = {
                Text(text = "Search Recipe Items...")
            },
            modifier = Modifier.background(
                MaterialTheme.colorScheme.onPrimary
            ).fillMaxWidth(),
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                focusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
                cursorColor = MaterialTheme.colorScheme.primaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
            )
        )

        when {
            uiState.isLoading -> {
                Loader()
            }
            uiState.error != null -> {
                Text(
                    text = "No Recipe Items found",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            uiState.success && uiState.results.isNotEmpty() -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = innerPadding.calculateTopPadding())

                ) {
                    itemsIndexed(uiState.results, key = { _, it -> it.id }) { index, meal ->

                        val cardPaddingStart = if (index % 2 == 0) 16.dp else 0.dp
                        val cardPaddingEnd = if (index % 2 == 0) 0.dp else 16.dp

                        val imageModifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .clip(RoundedCornerShape(16.dp))
                        MealCard(
                            meal,
                            modifier = Modifier.padding(
                                start = cardPaddingStart,
                                end = cardPaddingEnd
                            ),
                            imageModifier = imageModifier.clickable {
                                uiEvent.onNavigateToDetail(meal.id)
                            }
                        )
                    }
                }
            }
        }
    }
}