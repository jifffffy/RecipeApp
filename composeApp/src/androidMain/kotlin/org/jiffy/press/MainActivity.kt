package org.jiffy.press

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                androidStatusBarSideEffect = { statusBarColor, isDarkTheme ->
                    val view = LocalView.current

                    if (!view.isInEditMode) {
                        SideEffect {
                            val window = (view.context as Activity).window
                            @Suppress("DEPRECATION")
                            window.statusBarColor = statusBarColor
                            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme
                        }
                    }
                },
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}