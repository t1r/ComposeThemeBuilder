package dev.t1r.themebuilder.feature.baselinecolor.integration

import dev.t1r.themebuilder.data.colors.ThemeColorsModel
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.feature.baselinecolor.store.BaselineColorsStore

internal val stateToModel: BaselineColorsStore.State.() -> BaselineColorsComponent.Model =
    {
        BaselineColorsComponent.Model(
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
