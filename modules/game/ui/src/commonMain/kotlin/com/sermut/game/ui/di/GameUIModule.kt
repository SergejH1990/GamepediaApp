package com.sermut.game.ui.di

import com.sermut.game.ui.game.GameViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun GetGameUIModule() = module {
    viewModel { GameViewModel(gameUseCase = get()) }
}