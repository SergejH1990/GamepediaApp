package com.sermut.game.domain.di

import com.sermut.game.domain.usecases.GetGameDetailsUseCase
import com.sermut.game.domain.usecases.GetGamesUseCase
import org.koin.dsl.module

fun GetGameDomainModule() = module {
    factory { GetGamesUseCase(gameRepository = get()) }
    factory { GetGameDetailsUseCase(gameRepository = get()) }
}