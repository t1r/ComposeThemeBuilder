package dev.t1r.themebuilder.data.colors.theme

import dev.t1r.themebuilder.data.ThemePaletteDbModel
import dev.t1r.themebuilder.entity.colors.ThemeColors

internal fun mapDbToThemeColors(
    from: ThemePaletteDbModel,
): ThemeColors {
    return ThemeColors(
        id = from.uid,
        primary = from.primaryColor,
        primaryVariant = from.primaryVariant,
        secondary = from.secondary,
        secondaryVariant = from.secondaryVariant,
        background = from.background,
        surface = from.surface,
        error = from.error,
        onPrimary = from.onPrimary,
        onSecondary = from.onSecondary,
        onBackground = from.onBackground,
        onSurface = from.onSurface,
        onError = from.onError,
        isLight = from.isLight,
    )
}
