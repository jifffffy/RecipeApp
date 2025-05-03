package org.jiffy.press.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.jiffy.press.ui.composehelper.collectAsStateMultiplatform
import org.jiffy.press.ui.composehelper.getScreenSizeInfo
import org.jiffy.press.ui.destinations.home.HomeScreen
import org.jiffy.press.ui.destinations.home.HomeUIEvent
import org.jiffy.press.ui.destinations.home.HomeViewModel
import org.jiffy.press.ui.destinations.search.SearchScreen
import org.jiffy.press.ui.destinations.search.SearchUIEvent
import org.jiffy.press.ui.destinations.search.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.mp.KoinPlatform.getKoin

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    lastDoubleTappedNavItem: AppDestination?,
    onShowSnackbar: suspend (String) -> Unit,
    onScrolledToTop: (AppDestination) -> Unit,
) {
    val navigateToSearch = {
        navController.navigate(AppDestination.SEARCH.name) {
            /*navController.graph.startDestinationRoute?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
            launchSingleTop = true*/
        }
    }

    val navigateToDetail = { mealId: String ->
        navController.navigate(AppDestination.DETAIL.name)
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppDestination.getStartDestination().name,
    ) {
        composable(route = AppDestination.HOME.name) {
            val viewModel: HomeViewModel = viewModel { getKoin().get() }
            val uiState by viewModel.uiState.collectAsStateMultiplatform()


            LaunchedEffect(lastDoubleTappedNavItem) {
                val enabled = lastDoubleTappedNavItem?.equals(AppDestination.HOME) ?: false
                viewModel.requestScrollToTop(enabled = enabled)
            }

            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = uiState,
                uiEvent = HomeUIEvent(
                    onRefresh = viewModel::onRefresh,
                    onNavigateToSearch = navigateToSearch,
                    onNavigateToDetail = navigateToDetail,
                )
            )
        }
        composable(route = AppDestination.SEARCH.name) {
            val viewModel: SearchViewModel = viewModel { getKoin().get() }
            val uiState by viewModel.uiState.collectAsStateMultiplatform()
            SearchScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = uiState,
                uiEvent = SearchUIEvent(
                    onNavigateBack = {
                        navController.navigate(AppDestination.HOME.name)
                    },
                    onSearchTextChanged = viewModel::onSearchTextChanged,
                    onNavigateToDetail = navigateToDetail
                )
            )
        }
        composable(route = AppDestination.FAVORITES.name) {}
        composable(route = AppDestination.PROFILE.name) {}
    }

}
