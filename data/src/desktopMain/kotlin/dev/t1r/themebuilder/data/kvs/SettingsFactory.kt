package dev.t1r.themebuilder.data.kvs

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

actual class SettingsFactory(
    private val preferences: Preferences,
) {
    actual fun createSettings(): Settings {
        return PreferencesSettings(preferences)
    }
}