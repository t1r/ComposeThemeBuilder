package dev.t1r.themebuilder.data.colors.theme

import dev.t1r.themebuilder.entity.colors.ThemeColors

internal fun mapThemeColors(
    from: ThemeColorsModel,
): ThemeColors {
    return ThemeColors(
        primary = from.primary,
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
