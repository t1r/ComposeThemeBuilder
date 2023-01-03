package dev.t1r.themebuilder.feature.materialcolorspallet.integration

import dev.t1r.themebuilder.entity.colors.getColorByThemeColorMarker
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent.ContentState
import dev.t1r.themebuilder.feature.materialcolorspallet.store.MaterialColorsPalletStore

internal val stateToModel: MaterialColorsPalletStore.State.() -> MaterialColorsPalletComponent.Model =
    {
        val contentState = if (themeColorToChange != null) {
            val newColor = getColorByThemeColorMarker(themeColorToChange.marker, themeColorsModel)
            ContentState.SelectedMode(
                model = themeColorToChange.marker,
                newColor = newColor,
                previousColor = themeColorToChange.previousColor,
                oppositeColor = themeColorToChange.oppositeColor,
                newColorText = newTextColor ?: newColor.toString(16),
            )
        } else ContentState.Normal

        MaterialColorsPalletComponent.Model(
            colors = themeColorsModel,
            materialColors = materialColors,
            contentState = contentState,
        )
    }
