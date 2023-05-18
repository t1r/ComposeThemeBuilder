package dev.t1r.themebuilder.data.kvs

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.PreferencesSettings
import java.util.prefs.Preferences

actual class SettingsFactory(
    private val preferences: Preferences,
) {
    actual fun createSettings(): ObservableSettings {
        return PreferencesSettings(preferences)
    }
}