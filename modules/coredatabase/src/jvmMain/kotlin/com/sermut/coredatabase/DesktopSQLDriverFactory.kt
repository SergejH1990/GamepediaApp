package com.sermut.coredatabase

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.sermut.coredatabase.GameDB
import java.io.File

actual class SqlDriverFactory actual constructor(context: Any?) {

    actual fun GetSQLDriver() : SqlDriver {
        val driver = JdbcSqliteDriver(
            url = "jdbc:sqlite:GameDB.db"
        )

        if (!File("GameDB.db").exists()) {
            GameDB.Schema.create(driver)
        }

        return driver
    }
}