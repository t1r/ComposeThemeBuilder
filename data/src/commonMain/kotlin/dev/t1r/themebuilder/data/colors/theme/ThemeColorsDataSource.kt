package dev.t1r.themebuilder.data.colors.theme

class ThemeColorsDataSource {
    internal fun provideDefaultColors(): ThemeColorsModel {
        return ThemeColorsModel(
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