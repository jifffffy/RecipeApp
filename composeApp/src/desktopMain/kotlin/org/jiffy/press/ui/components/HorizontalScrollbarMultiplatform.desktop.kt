package org.jiffy.press.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.v2.ScrollbarAdapter
import androidx.compose.foundation.v2.maxScrollOffset
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jiffy.press.ui.composehelper.getScreenSizeInfo
import org.jiffy.press.ui.theme.AppTheme
import org.jiffy.press.ui.theme.getDimension

@Composable
actual fun HorizontalScrollbarMultiplatform(
    modifier: Modifier,
    enabled: Boolean,
    scrollState: ScrollState,
    content: @Composable (contentModifier: Modifier) -> Unit
) {
    HorizontalScrollbarMultiplatform(
        modifier = modifier,
        enabled = enabled,
        scrollbarAdapter = rememberScrollbarAdapter(scrollState = scrollState),
        content = content
    )
}

@Composable
actual fun HorizontalScrollbarMultiplatform(
    modifier: Modifier,
    enabled: Boolean,
    lazyListState: LazyListState,
    content: @Composable (contentModifier: Modifier) -> Unit
) {
    HorizontalScrollbarMultiplatform(
        modifier = modifier,
        enabled = enabled,
        scrollbarAdapter = rememberScrollbarAdapter(scrollState = lazyListState),
        content = content
    )
}

@Composable
actual fun HorizontalScrollbarMultiplatform(
    modifier: Modifier,
    enabled: Boolean,
    lazyGridState: LazyGridState,
    content: @Composable (contentModifier: Modifier) -> Unit
) {
    HorizontalScrollbarMultiplatform(
        modifier = modifier,
        enabled = enabled,
        scrollbarAdapter = rememberScrollbarAdapter(scrollState = lazyGridState),
        content = content
    )
}

@Composable
private fun HorizontalScrollbarMultiplatform(
    modifier: Modifier,
    enabled: Boolean,
    scrollbarAdapter: ScrollbarAdapter,
    content: @Composable (contentModifier: Modifier) -> Unit,
) {
    val dimension = getScreenSizeInfo().getDimension()
    val density = LocalDensity.current

    var isScrollbarVisible by remember { mutableStateOf(false) }

    // Debounce mechanism to stabilize the scrollbar visibility state
    LaunchedEffect(scrollbarAdapter.maxScrollOffset) {
        with(density) {
            if (scrollbarAdapter.maxScrollOffset > dimension.grid_3.roundToPx()) {
                isScrollbarVisible = true
            } else {
                delay(1000) // Adjust the delay as needed
                if (scrollbarAdapter.maxScrollOffset == 0.0) {
                    isScrollbarVisible = false
                }
            }
        }
    }

    Column (
        modifier = modifier,
    ) {
        content(Modifier.weight(weight = 1f))

        AnimatedVisibility(visible = enabled && isScrollbarVisible) {
            Column {
                HorizontalDivider(
                    modifier = Modifier.fillMaxHeight(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                )

                HorizontalScrollbar(
                    adapter = scrollbarAdapter,
                    style = LocalScrollbarStyle.current.copy(
                        thickness = dimension.grid_1,
                        unhoverColor = MaterialTheme.colorScheme.outlineVariant.copy(
                            alpha = 0.5f,
                        ),
                        hoverColor = MaterialTheme.colorScheme.outline.copy(
                            alpha = 0.5f,
                        ),
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(color = MaterialTheme.colorScheme.surface)
                        .padding(horizontal = dimension.grid_0_5),
                )
            }
        }
    }
}

@Composable
@Preview
private fun ScrollbarMultiplatformPreview() {
    AppTheme {
        val lazyListState = rememberLazyListState()
        HorizontalScrollbarMultiplatform(
            lazyListState = lazyListState,
        ) {
            LazyRow(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
            ) {
                repeat(20) {
                    item {
                        Text(
                            modifier = Modifier.height(height = 48.dp),
                            text = "Sample item",
                        )
                    }
                }
            }
        }
    }
}
