package org.jiffy.press.ui.destinations.search

data class SearchUIEvent(
    val onNavigateBack: () -> Unit,
    val onSearchTextChanged: (String) -> Unit,
    val onNavigateToDetail: (String) -> Unit,
)
