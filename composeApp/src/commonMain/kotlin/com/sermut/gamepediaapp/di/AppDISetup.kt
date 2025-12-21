package com.sermut.gamepediaapp.di

import com.sermut.coredatabase.di.GetCoreDatabaseModule
import com.sermut.corenetwork.di.GetCoreNetworkModule
import com.sermut.favorite.data.di.GetFavoriteDataModule
import com.sermut.favorite.domain.di.GetFavoriteDomainModule
import com.sermut.favorite.ui.di.GetFavoriteUIModule
import com.sermut.game.data.di.GetGameDataModule
import com.sermut.game.domain.di.GetGameDomainModule
import com.sermut.game.ui.di.GetGameUIModule
import com.sermut.search.data.di.GetSearchDataModule
import com.sermut.search.domain.di.GetSearchDomainModule
import com.sermut.search.ui.di.GetSearchUIModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null){
    startKoin {
        koinApplication?.invoke(this)
        modules(
            GetCoreDatabaseModule(),
            GetCoreNetworkModule(),
            GetGameDataModule(),
            GetGameDomainModule(),
            GetGameUIModule(),
            GetSearchDataModule(),
            GetSearchDomainModule(),
            GetSearchUIModule(),
            GetFavoriteDataModule(),
            GetFavoriteDomainModule(),
            GetFavoriteUIModule()
        )
    }
}