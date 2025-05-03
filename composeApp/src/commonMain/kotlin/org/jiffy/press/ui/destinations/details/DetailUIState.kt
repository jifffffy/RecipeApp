package org.jiffy.press.ui.destinations.details

import org.jiffy.press.domain.model.meal.Meal

data class DetailUIState(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null,
    val meal: Meal? = null,
)
