package org.jiffy.press.ui.navigation

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import recipeapp.composeapp.generated.resources.Res
import recipeapp.composeapp.generated.resources.navigation_account
import recipeapp.composeapp.generated.resources.navigation_agile
import recipeapp.composeapp.generated.resources.navigation_tariffs
import recipeapp.composeapp.generated.resources.navigation_usage

import recipeapp.composeapp.generated.resources.bar_chart
import recipeapp.composeapp.generated.resources.pulse
import recipeapp.composeapp.generated.resources.coin
import recipeapp.composeapp.generated.resources.user

enum class AppDestination(val titleResId: StringResource, val iconResId: DrawableResource) {
    USAGE(titleResId = Res.string.navigation_usage, iconResId = Res.drawable.bar_chart),
    AGILE(titleResId = Res.string.navigation_agile, iconResId = Res.drawable.pulse),
    TARIFFS(titleResId = Res.string.navigation_tariffs, iconResId = Res.drawable.coin),
    ACCOUNT(titleResId = Res.string.navigation_account, iconResId = Res.drawable.user),
    ;

    companion object {
        fun getNavBarDestinations(): List<AppDestination> = listOf(AGILE, USAGE, TARIFFS, ACCOUNT)
        fun getStartDestination() = AGILE
    }
}