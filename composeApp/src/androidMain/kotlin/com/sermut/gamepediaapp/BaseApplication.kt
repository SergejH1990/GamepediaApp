package com.sermut.gamepediaapp

import android.app.Application
import com.sermut.gamepediaapp.di.initKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}