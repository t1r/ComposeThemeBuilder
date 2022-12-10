package dev.t1r.themebuilder.data.colors

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ThemeColorsDataSourceImpl : ThemeColorsDataSource {
    private val themeColors = MutableStateFlow(provideDefaultColors())

    override fun themeColorsState(): StateFlow<ThemeColors> = themeColors

    private fun provideDefaultColors(): ThemeColors {
        return ThemeColors(
            primary = 0xFF6200EE,
            primaryVariant = 0xFF3700B3,
            secondary = 0xFF03DAC6,
            secondaryVariant = 0xFF018786,
            background = 0xFFFFFFFF,
            surface = 0xFFFFFFFF,
            error = 0xFFB00020,
            onPrimary = 0xFFFFFFFF,
            onSecondary = 0xFF000000,
            onBackground = 0xFF000000,
            onSurface = 0xFF000000,
            onError = 0xFFFFFFFF,
            isLight = false,
        )
    }
}