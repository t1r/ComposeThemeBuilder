package dev.t1r.themebuilder.data.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dev.t1r.themebuilder.data.ThemeBuilderDb

actual class DriverFactory(
    private val context: Context,
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            schema = ThemeBuilderDb.Schema,
            context = context,
            name = "theme_builder.db",
        )
    }
}