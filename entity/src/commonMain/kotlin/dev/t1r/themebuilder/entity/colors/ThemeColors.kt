package dev.t1r.themebuilder.entity.colors

data class ThemeColors(
    val primary: Long = 0xFF6200EE,
    val primaryVariant: Long = 0xFF3700B3,
    val secondary: Long = 0xFF03DAC6,
    val secondaryVariant: Long = 0xFF018786,
    val background: Long = 0xFFFFFFFF,
    val surface: Long = 0xFFFFFFFF,
    val error: Long = 0xFFB00020,
    val onPrimary: Long = 0xFFFFFFFF,
    val onSecondary: Long = 0xFF000000,
    val onBackground: Long = 0xFF000000,
    val onSurface: Long = 0xFF000000,
    val onError: Long = 0xFFFFFFFF,
    val isLight: Boolean = true,
)

sealed class ThemeColorsEnum {
    object Primary : ThemeColorsEnum()
    object PrimaryVariant : ThemeColorsEnum()
    object Secondary : ThemeColorsEnum()
    object SecondaryVariant : ThemeColorsEnum()
    object Background : ThemeColorsEnum()
    object Surface : ThemeColorsEnum()
    object Error : ThemeColorsEnum()
    object OnPrimary : ThemeColorsEnum()
    object OnSecondary : ThemeColorsEnum()
    object OnBackground : ThemeColorsEnum()
    object OnSurface : ThemeColorsEnum()
    object OnError : ThemeColorsEnum()
}