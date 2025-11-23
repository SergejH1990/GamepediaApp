package com.sermut.gamepediaapp.di

import com.sermut.corenetwork.di.GetCoreNetworkModule
import com.sermut.game.data.di.GetGameDataModule
import com.sermut.game.domain.di.GetGameDomainModule
import com.sermut.game.ui.di.GetGameUIModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null){
    startKoin {
        modules(
            GetCoreNetworkModule(),
            GetGameDataModule(),
            GetGameDomainModule(),
            GetGameUIModule()
        )
    }
}