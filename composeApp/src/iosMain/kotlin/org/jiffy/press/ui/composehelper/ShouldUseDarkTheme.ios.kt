package org.jiffy.press.ui.composehelper

import androidx.compose.runtime.Composable
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

@Composable
actual fun shouldUseDarkTheme(): Boolean {
    val uiStyle = UIScreen.mainScreen.traitCollection.userInterfaceStyle
    return uiStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
}