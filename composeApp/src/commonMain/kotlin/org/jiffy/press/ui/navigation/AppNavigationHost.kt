package org.jiffy.press.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    lastDoubleTappedNavItem: AppDestination?,
    onShowSnackbar: suspend (String) -> Unit,
    onScrolledToTop: (AppDestination) -> Unit,
) {
    val navigateToAccountTab = {
        navController.navigate(AppDestination.ACCOUNT.name) {
            navController.graph.startDestinationRoute?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
            launchSingleTop = true
        }
    }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppDestination.getStartDestination().name,
    ) {
        composable(route = AppDestination.USAGE.name) {}
        composable(route = AppDestination.AGILE.name) {}
    }

}
