package com.sermut.gamepediaapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sermut.gamepediaapp.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "GamepediaApp",
    ) {
        initKoin()
        App()
    }
}