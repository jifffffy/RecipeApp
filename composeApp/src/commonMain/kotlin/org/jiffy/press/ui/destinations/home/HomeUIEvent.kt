package org.jiffy.press.ui.destinations.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeUIEvent(
    val onRefresh: () -> Unit,
    val onNavigateToSearch: ()-> Unit,
    val onNavigateToDetail: (String)-> Unit,
) {
}