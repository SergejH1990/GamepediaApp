package com.sermut.favorite.data.di

import com.sermut.favorite.data.repository.FavoriteRepositoryImplementation
import com.sermut.favorite.domain.repository.FavoriteRepository
import org.koin.dsl.module

fun GetFavoriteDataModule() = module {
    factory<FavoriteRepository> {
        FavoriteRepositoryImplementation(gameDB = get())
    }
}