package org.jiffy.press

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import org.jiffy.press.di.dataSourceModule
import org.jiffy.press.di.dispatcherModule
import org.jiffy.press.di.ktorModule
import org.jiffy.press.di.repositoryModule
import org.jiffy.press.di.useCaseModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication

fun main()  {
    koinApplication {
        printLogger(Level.ERROR)
        logger(KermitKoinLogger(Logger.withTag("koin")))
        modules(
            dispatcherModule,
            ktorModule,
        )
    }
    startKoin {
        modules(
            dataSourceModule,
            dispatcherModule,
            ktorModule,
            repositoryModule,
            useCaseModule,
        )
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "RecipeApp",
        ) {
            App()
        }
    }
}