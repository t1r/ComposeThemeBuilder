package dev.t1r.themebuilder.feature.root.integration

import dev.t1r.themebuilder.data.colors.ThemeColorsModel
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.feature.root.RootComponent
import dev.t1r.themebuilder.feature.root.store.RootStore

internal val stateToModel: RootStore.State.() -> RootComponent.Model =
    {
        RootComponent.Model(
            colors = map(model),
        )
    }

private fun map(
    from: ThemeColorsModel?,
): ThemeColors {
    from ?: return ThemeColors()
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
