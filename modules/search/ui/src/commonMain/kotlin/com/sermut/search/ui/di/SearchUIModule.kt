package com.sermut.search.ui.di

import com.sermut.search.ui.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun GetSearchUIModule() = module {
    viewModel { SearchViewModel(searchGamesUseCase = get()) }
}