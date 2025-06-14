package dev.t1r.themebuilder.component.materialcolorspalette

import dev.t1r.themebuilder.entity.colors.ColorGroup
import dev.t1r.themebuilder.entity.colors.ColorModel
import dev.t1r.themebuilder.entity.colors.PaletteThemeColors
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import kotlinx.coroutines.flow.Flow

interface MaterialColorsPaletteComponent {
    val models: Flow<Model>
    val events: Flow<Event>

    data class Model(
        val colors: ThemeColors = ThemeColors(),
        val materialColors: List<ColorGroup> = emptyList(),
        val contentState: ContentState = ContentState.Normal,
        val paletteList: List<PaletteThemeColors> = emptyList(),
    )

    sealed interface ContentState {
        data object Normal : ContentState
        data class ChangeColorMode(
            val model: ThemeColorsEnum,
            val newColor: Long,
            val previousColor: Long,
            val oppositeColor: Long,
            val newColorText: String = "",
        ) : ContentState

        data object PaletteList : ContentState
    }

    sealed interface Event {
        data object Error : Event
    }

    fun onThemeColorToChangeSelected(color: ThemeColorsEnum)
    fun onChangeThemeModeClicked()
    fun onColorCandidateSelected(themeColor: ThemeColorsEnum, color: ColorModel)
    fun onCancelSelectClicked()
    fun onConfirmSelectedClicked()
    fun onTextColorChanged(themeColor: ThemeColorsEnum, text: String)
    fun onChangePaletteClicked()
    fun onBackToPaletteClicked()
    fun onAddPaletteClicked()
    fun onPaletteClicked(id: Long)
    fun onDeleteClicked(id: Long)
}