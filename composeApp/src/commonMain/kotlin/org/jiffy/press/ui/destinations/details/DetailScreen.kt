package org.jiffy.press.ui.destinations.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jiffy.press.domain.model.meal.Meal
import org.jiffy.press.ui.components.ErrorContent
import org.jiffy.press.ui.components.Loader

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailUIState,
) {
    when {
        uiState.isLoading -> {
            Loader()
        }

        uiState.error !== null -> {
            ErrorContent()
        }

        uiState.meal != null -> {
            DetailScreenContent(modifier, uiState)
        }
    }

}

@Composable
fun DetailScreenContent(
    modifier: Modifier,
    uiState: DetailUIState,
) {
    val scrollState: LazyListState = rememberLazyListState()
    uiState.meal?.let { meal ->
        LazyColumn(state = scrollState, modifier = modifier.fillMaxSize().padding(bottom = 90.dp)) {
            item(key = "MealHeader") {
                MealHeader(meal, uiState)
            }
        }
    }

}

@Composable
fun MealHeader(meal: Meal, uiState: DetailUIState,) {
    Box(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = meal.thumbnailUrl,
            contentDescription = meal.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(250.dp).clip(
                RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp,
                )
            )
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(WindowInsets.statusBars.asPaddingValues())
                .padding(
                    vertical = 32.dp
                ).padding(horizontal = 16.dp)
                .align(Alignment.TopCenter)
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.padding(horizontal = 8.dp).size(30.dp).background(
                    color = MaterialTheme.colorScheme.background.copy(
                        alpha = 0.8f
                    ),
                    shape = CircleShape
                )
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
