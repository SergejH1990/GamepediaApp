package com.sermut.favorite.ui.di

import com.sermut.favorite.ui.FavoriteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun GetFavoriteUIModule() = module {
    viewModel{
        FavoriteViewModel(
            deleteUseCase = get(),
            getAllLocalGamesUseCase = get()
        )
    }
}