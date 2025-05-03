package org.jiffy.press.ui.destinations.search

import org.jiffy.press.domain.model.meal.Meal

data class SearchUIState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
    val results: List<Meal> = emptyList(),
    val searchText: String = "",
)
