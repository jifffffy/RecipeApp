package org.jiffy.press.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun VerticalScrollbarMultiplatform(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    scrollState: ScrollState,
    content: @Composable (contentModifier: Modifier) -> Unit,
)

@Composable
expect fun VerticalScrollbarMultiplatform(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    lazyListState: LazyListState,
    content: @Composable (contentModifier: Modifier) -> Unit,
)

@Composable
expect fun VerticalScrollbarMultiplatform(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    lazyGridState: LazyGridState,
    content: @Composable (contentModifier: Modifier) -> Unit,
)