package com.sermut.game.ui.di

import com.sermut.game.ui.game.GameViewModel
import com.sermut.game.ui.gamedetails.GameDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun GetGameUIModule() = module {
    viewModel { GameViewModel(gameUseCase = get()) }
    viewModel { GameDetailsViewModel(gameDetailsUseCase = get()) }
}