package com.sermut.game.data.di

import com.sermut.game.data.repository.GameRepositoryImplementation
import com.sermut.game.domain.repository.GameRepository
import org.koin.dsl.module

fun GetGameDataModule() = module {
    factory<GameRepository> { GameRepositoryImplementation(apiService = get(), gameDB = get()) }
}