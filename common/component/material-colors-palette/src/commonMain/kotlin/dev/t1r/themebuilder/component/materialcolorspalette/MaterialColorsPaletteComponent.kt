package dev.t1r.themebuilder.component.materialcolorspalette

import dev.t1r.themebuilder.entity.colors.*
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

    sealed class ContentState {
        object Normal : ContentState()
        data class ChangeColorMode(
            val model: ThemeColorsEnum,
            val newColor: Long,
            val previousColor: Long,
            val oppositeColor: Long,
            val newColorText: String = "",
        ) : ContentState()

        object PaletteList : ContentState()
    }

    sealed class Event {
        object Error : Event()
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