package org.jiffy.press.ui.composehelper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.jiffy.press.ui.model.ScreenSizeInfo

@Composable
actual fun getScreenSizeInfo(): ScreenSizeInfo {
    val density = LocalDensity.current
    val config = LocalConfiguration.current

    val screenSizeInfo = remember(density, config) {
        val heightDp = config.screenHeightDp.dp
        val widthDp = config.screenWidthDp.dp
        ScreenSizeInfo(
            heightPx = with(density) { heightDp.roundToPx() },
            widthPx = with(density) { widthDp.roundToPx() },
            heightDp = heightDp,
            widthDp = widthDp,
        )
    }
    return screenSizeInfo
}