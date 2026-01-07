package com.sermut.search.domain.di

import com.sermut.search.domain.useCases.SearchGamesUseCase
import org.koin.dsl.module

fun GetSearchDomainModule() = module {
    factory { SearchGamesUseCase(searchRepository = get()) }
}