package dev.t1r.themebuilder.data.colors

data class ThemeColors(
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