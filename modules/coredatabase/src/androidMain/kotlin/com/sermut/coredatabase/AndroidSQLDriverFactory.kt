package com.sermut.coredatabase

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class SqlDriverFactory actual constructor(context: Any?) {

    private val context = context as Context
    actual fun GetSQLDriver() : SqlDriver {
        return AndroidSqliteDriver(
            schema = GameDB.Schema,
            context = context,
            name = "GameDB.db"
        )
    }
}