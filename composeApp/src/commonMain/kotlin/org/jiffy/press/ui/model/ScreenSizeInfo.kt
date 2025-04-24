package org.jiffy.press.ui.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
data class ScreenSizeInfo(
    val heightPx: Int,
    val widthPx: Int,
    val heightDp: Dp,
    val widthDp: Dp,
) {
    fun isPortrait() = widthPx < heightPx
}