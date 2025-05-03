package org.jiffy.press.ui.destinations.home

import org.jiffy.press.domain.model.meal.Meal

data class HomeUIState(
    val meals: List<Meal>? = null,
    val isLoading: Boolean = true,
    val error: String? = null,
    val requestScrollToTop: Boolean = false,
)
