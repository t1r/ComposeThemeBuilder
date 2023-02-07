package dev.t1r.themebuilder.data.kvs

import com.russhwolf.settings.ObservableSettings

expect class SettingsFactory {
    fun createSettings(): ObservableSettings
}