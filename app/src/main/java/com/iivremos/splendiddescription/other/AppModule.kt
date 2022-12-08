package com.iivremos.splendiddescription.other

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppModule: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppModule)
            modules(listOf(dataModule, viewModelModule))
        }
    }
}