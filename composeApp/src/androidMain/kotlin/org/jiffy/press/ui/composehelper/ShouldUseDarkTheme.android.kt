package org.jiffy.press.ui.composehelper

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
actual fun shouldUseDarkTheme(): Boolean {
    return isSystemInDarkTheme()
}