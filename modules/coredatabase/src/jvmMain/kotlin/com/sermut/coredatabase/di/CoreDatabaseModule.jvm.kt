package com.sermut.coredatabase.di

import com.sermut.coredatabase.SqlDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun GetCoreDatabaseModule(): Module {
    return module {
        single { SqlDriverFactory().GetSQLDriver() }
    }
}