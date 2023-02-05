package dev.t1r.themebuilder.data.kvs

import com.russhwolf.settings.Settings

expect class SettingsFactory {
    fun createSettings(): Settings
}