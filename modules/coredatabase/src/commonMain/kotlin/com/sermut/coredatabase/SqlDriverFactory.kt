package com.sermut.coredatabase

import app.cash.sqldelight.db.SqlDriver

expect class SqlDriverFactory(
    context: Any? = null
) {
    fun GetSQLDriver() : SqlDriver
}