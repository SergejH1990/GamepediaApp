package com.sermut.coredatabase.di

import app.cash.sqldelight.db.SqlDriver
import com.sermut.coredatabase.GameDB
import com.sermut.coredatabase.SqlDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun GetCoreDatabaseModule(): Module {
    return module {
        single { SqlDriverFactory().GetSQLDriver() }
        single { GameDB.invoke(get<SqlDriver>()) }
    }
}