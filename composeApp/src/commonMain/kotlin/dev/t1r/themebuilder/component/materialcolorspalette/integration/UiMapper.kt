package dev.t1r.themebuilder.component.materialcolorspalette.integration

import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent.ContentState
import dev.t1r.themebuilder.component.materialcolorspalette.store.MaterialColorsPaletteStore
import dev.t1r.themebuilder.entity.colors.PaletteThemeColors
import dev.t1r.themebuilder.entity.colors.getColorByThemeColorMarker

internal val stateToModel: MaterialColorsPaletteStore.State.() -> MaterialColorsPaletteComponent.Model =
    {
        val contentState = when {
            themeColorToChange != null -> {
                val newColor =
                    getColorByThemeColorMarker(themeColorToChange.marker, themeColorsModel)
                ContentState.ChangeColorMode(
                    model = themeColorToChange.marker,
                    newColor = newColor,
                    previousColor = themeColorToChange.previousColor,
                    oppositeColor = themeColorToChange.oppositeColor,
                    newColorText = newTextColor ?: newColor.toString(16),
                )
            }

            isPaletteListShowing -> ContentState.PaletteList
            else -> ContentState.Normal
        }
        val paletteList = paletteList.map { model ->
            PaletteThemeColors(
                model = model,
                isSelected = model.id == themeColorsModel.id,
            )
        }

        MaterialColorsPaletteComponent.Model(
            colors = themeColorsModel,
            materialColors = materialColors,
            contentState = contentState,
            paletteList = paletteList,
        )
    }
