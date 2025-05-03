package org.jiffy.press.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jiffy.press.ui.composehelper.getScreenSizeInfo
import org.jiffy.press.ui.navigation.AppDestination
import org.jiffy.press.ui.theme.getDimension
import recipeapp.composeapp.generated.resources.Res
import recipeapp.composeapp.generated.resources.content_description_navigation_bar


@Composable
fun AppBottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    onCurrentRouteSecondTapped: (item: AppDestination) -> Unit,
) {
    val dimension = getScreenSizeInfo().getDimension()

    val navigationBarContentDescription =
        stringResource(Res.string.content_description_navigation_bar)
    NavigationBar(
        modifier = modifier.semantics { contentDescription = navigationBarContentDescription },
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        for (item in AppDestination.getNavBarDestinations()) {
            val selected = currentRoute == item.name
            val itemContentDescription = stringResource(item.titleResId)
            NavigationBarItem(
                modifier = Modifier.semantics { contentDescription = itemContentDescription },
                selected = selected,
                onClick = {
                    if(!selected) {
                        navController.navigate(item.name) {
                            popUpTo(route = AppDestination.getStartDestination().name) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    } else {
                        onCurrentRouteSecondTapped(item)
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(size = dimension.navigationIconSize),
                        imageVector = item.icon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(resource = item.titleResId).uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CommonPreviewSetup {
        AppBottomNavigationBar(
            modifier = Modifier
                .wrapContentHeight()
                .padding(0.dp),
            navController = rememberNavController(),
            onCurrentRouteSecondTapped = {},
        )
    }
}