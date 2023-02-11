package dev.t1r.themebuilder.feature.materialcolorspalette.store

import com.arkivanov.mvikotlin.core.store.Store
import dev.t1r.themebuilder.entity.colors.ColorGroup
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import dev.t1r.themebuilder.feature.materialcolorspalette.store.MaterialColorsPaletteStore.*

interface MaterialColorsPaletteStore : Store<Intent, State, Label> {
    data class State(
        val themeColorsModel: ThemeColors = ThemeColors(),
        val materialColors: List<ColorGroup> = emptyList(),
        val themeColorToChange: ThemeColorToChange? = null,
        val newTextColor: String? = null,
        val isPaletteListShowing: Boolean = false,
    )

    data class ThemeColorToChange(
        val marker: ThemeColorsEnum,
        val previousColor: Long,
        val oppositeColor: Long,
    )

    sealed class Action {
        data class UpdateThemeColors(val model: ThemeColors) : Action()
        data class UpdateMaterialColors(val list: List<ColorGroup>) : Action()
    }

    sealed class Intent {
        data class SelectThemeColorToChange(val color: ThemeColorsEnum) : Intent()
        data class SelectColorCandidate(val themeColor: ThemeColorsEnum, val color: Long) : Intent()
        object ChangeThemeMode : Intent()
        object CancelSelectColor : Intent()
        object ConfirmSelectedColor : Intent()
        data class ChangeTextColor(val themeColor: ThemeColorsEnum, val text: String) : Intent()
        object OpenPaletteList : Intent()
        object ClosePaletteList : Intent()
    }

    sealed class Label
}