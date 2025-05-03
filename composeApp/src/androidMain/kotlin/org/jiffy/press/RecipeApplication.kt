package org.jiffy.press

import android.app.Application
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import org.jiffy.press.di.dataSourceModule
import org.jiffy.press.di.dispatcherModule
import org.jiffy.press.di.ktorModule
import org.jiffy.press.di.platformModule
import org.jiffy.press.di.repositoryModule
import org.jiffy.press.di.useCaseModule
import org.jiffy.press.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level


class RecipeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@RecipeApplication)
            logger(
                KermitKoinLogger(Logger.withTag("koin")),
            )
            modules(
                platformModule,
                dispatcherModule,
                viewModelModule,
                dataSourceModule,
                useCaseModule,
                ktorModule,
                repositoryModule,
            )
        }
    }
}