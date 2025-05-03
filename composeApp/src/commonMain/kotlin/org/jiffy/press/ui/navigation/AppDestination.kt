package org.jiffy.press.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import recipeapp.composeapp.generated.resources.navigation_profile
import org.jetbrains.compose.resources.StringResource
import recipeapp.composeapp.generated.resources.Res
import recipeapp.composeapp.generated.resources.navigation_home
import recipeapp.composeapp.generated.resources.navigation_search
import recipeapp.composeapp.generated.resources.navigation_detail
import recipeapp.composeapp.generated.resources.navigation_favorites


enum class AppDestination(val titleResId: StringResource, val icon: ImageVector) {
    HOME(titleResId = Res.string.navigation_home, icon = Icons.Default.Home),
    FAVORITES(titleResId = Res.string.navigation_favorites, icon = Icons.Default.Favorite),
    SEARCH(titleResId = Res.string.navigation_search, icon = Icons.Default.Search),
    DETAIL(titleResId = Res.string.navigation_detail, icon = Icons.Default.Details),
    PROFILE(titleResId = Res.string.navigation_profile, icon = Icons.Default.AccountCircle),
    ;

    companion object {
        fun getNavBarDestinations(): List<AppDestination> = listOf(HOME, SEARCH, FAVORITES, PROFILE)
        fun getStartDestination() = HOME
    }
}