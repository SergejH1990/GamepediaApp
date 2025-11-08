package com.sermut.gamepediaapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform