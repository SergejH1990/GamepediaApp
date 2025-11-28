package com.sermut.search.data.di

import com.sermut.search.data.repository.SearchRepositoryImplementation
import com.sermut.search.domain.repository.SearchRepository
import org.koin.dsl.module

fun GetSearchDataModule() = module {
    factory<SearchRepository>{ SearchRepositoryImplementation(apiService = get()) }
}