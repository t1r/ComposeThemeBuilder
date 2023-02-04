package dev.t1r.themebuilder.data.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dev.t1r.themebuilder.data.ThemeBuilderDb

actual class DriverFactory {
    actual fun create(): SqlDriver {
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        ThemeBuilderDb.Schema.create(driver)
        return driver
    }
}