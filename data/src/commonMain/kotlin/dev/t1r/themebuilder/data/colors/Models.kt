package dev.t1r.themebuilder.data.colors

internal data class ThemeColorsModel(
    val primary: Long,
    val primaryVariant: Long,
    val secondary: Long,
    val secondaryVariant: Long,
    val background: Long,
    val surface: Long,
    val error: Long,
    val onPrimary: Long,
    val onSecondary: Long,
    val onBackground: Long,
    val onSurface: Long,
    val onError: Long,
    val isLight: Boolean,
)