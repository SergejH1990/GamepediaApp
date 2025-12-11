package com.sermut.favorite.domain.di

import com.sermut.favorite.domain.useCases.DeleteUseCase
import com.sermut.favorite.domain.useCases.GetAllLocalGamesUseCase
import com.sermut.favorite.domain.useCases.UpsertUseCase
import org.koin.dsl.module

fun GetFavoriteDomainModule() = module {
    factory { DeleteUseCase(favoriteRepository = get()) }
    factory { UpsertUseCase(favoriteRepository = get()) }
    factory { GetAllLocalGamesUseCase(favoriteRepository = get()) }
}