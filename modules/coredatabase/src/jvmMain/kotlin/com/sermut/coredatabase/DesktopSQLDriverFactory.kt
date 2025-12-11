package com.sermut.coredatabase

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

actual class SqlDriverFactory actual constructor(context: Any?) {

    actual fun GetSQLDriver() : SqlDriver {
        return JdbcSqliteDriver(
            "jdbc:sqlite:GameDB.db"
        )
    }
}