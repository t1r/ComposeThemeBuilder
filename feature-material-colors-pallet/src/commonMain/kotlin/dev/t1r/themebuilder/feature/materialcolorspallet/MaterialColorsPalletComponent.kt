package dev.t1r.themebuilder.feature.materialcolorspallet

import dev.t1r.themebuilder.entity.colors.ColorGroup
import dev.t1r.themebuilder.entity.colors.ColorModel
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import kotlinx.coroutines.flow.Flow

interface MaterialColorsPalletComponent {
    val models: Flow<Model>

    data class Model(
        val colors: ThemeColors = ThemeColors(),
        val materialColors: List<ColorGroup> = emptyList(),
        val contentState: ContentState = ContentState.Normal,
    )

    sealed class ContentState {
        object Normal : ContentState()
        data class SelectedMode(val model: ThemeColorsEnum) : ContentState()
    }

    fun onThemeColorToChangeSelected(color: ThemeColorsEnum)
    fun onColorSelected(themeColor: ThemeColorsEnum, color: ColorModel)
}