package dev.t1r.themebuilder.data.kvs

import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

actual class SettingsFactory(
    private val sharedPref: SharedPreferences,
) {
    actual fun createSettings(): Settings {
        return SharedPreferencesSettings(sharedPref)
    }
}