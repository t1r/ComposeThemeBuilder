package dev.t1r.themebuilder.entity.colors

data class ThemeColors(
    val id: Long = 1L,
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

sealed class ThemeColorsEnum(val title: String) {
    data object Primary : ThemeColorsEnum("Primary")
    data object PrimaryVariant : ThemeColorsEnum("Primary Variant")
    data object Secondary : ThemeColorsEnum("Secondary")
    data object SecondaryVariant : ThemeColorsEnum("Secondary Variant")
    data object Background : ThemeColorsEnum("Background")
    data object Surface : ThemeColorsEnum("Surface")
    data object Error : ThemeColorsEnum("Error")
    data object OnPrimary : ThemeColorsEnum("On Primary")
    data object OnSecondary : ThemeColorsEnum("On Secondary")
    data object OnBackground : ThemeColorsEnum("On Background")
    data object OnSurface : ThemeColorsEnum("On Surface")
    data object OnError : ThemeColorsEnum("On Error")
}

fun getColorByThemeColorMarker(
    marker: ThemeColorsEnum,
    themeColors: ThemeColors,
): Long {
    return when (marker) {
        ThemeColorsEnum.Background -> themeColors.background
        ThemeColorsEnum.Error -> themeColors.error
        ThemeColorsEnum.OnBackground -> themeColors.onBackground
        ThemeColorsEnum.OnError -> themeColors.onError
        ThemeColorsEnum.OnPrimary -> themeColors.onPrimary
        ThemeColorsEnum.OnSecondary -> themeColors.onSecondary
        ThemeColorsEnum.OnSurface -> themeColors.onSurface
        ThemeColorsEnum.Primary -> themeColors.primary
        ThemeColorsEnum.PrimaryVariant -> themeColors.primaryVariant
        ThemeColorsEnum.Secondary -> themeColors.secondary
        ThemeColorsEnum.SecondaryVariant -> themeColors.secondaryVariant
        ThemeColorsEnum.Surface -> themeColors.surface
    }
}

fun getOppositeColorByThemeColorMarker(
    marker: ThemeColorsEnum,
    themeColors: ThemeColors,
): Long {
    return when (marker) {
        ThemeColorsEnum.Background -> themeColors.onBackground
        ThemeColorsEnum.Error -> themeColors.onError
        ThemeColorsEnum.OnBackground -> themeColors.background
        ThemeColorsEnum.OnError -> themeColors.error
        ThemeColorsEnum.OnPrimary -> themeColors.primary
        ThemeColorsEnum.OnSecondary -> themeColors.secondary
        ThemeColorsEnum.OnSurface -> themeColors.surface
        ThemeColorsEnum.Primary -> themeColors.onPrimary
        ThemeColorsEnum.PrimaryVariant -> themeColors.onPrimary
        ThemeColorsEnum.Secondary -> themeColors.onSecondary
        ThemeColorsEnum.SecondaryVariant -> themeColors.onSecondary
        ThemeColorsEnum.Surface -> themeColors.onSurface
    }
}

data class PaletteThemeColors(
    val model: ThemeColors,
    val isSelected: Boolean,
)