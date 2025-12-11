package com.sermut.gamepediaapp

import android.app.Application
import com.sermut.gamepediaapp.di.initKoin
import org.koin.dsl.module

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            it.modules(
                modules = module {
                    single { this@BaseApplication.applicationContext }
                }
            )
        }
    }
}