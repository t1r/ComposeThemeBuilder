package dev.t1r.themebuilder.data.db

import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineScope

actual class DriverFactory {
    actual fun create(): SqlDriver {
        return TODO()
    }
}