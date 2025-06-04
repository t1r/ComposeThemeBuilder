package dev.t1r.themebuilder.component.materialcolorspalette.store

import com.arkivanov.mvikotlin.core.store.Store
import dev.t1r.themebuilder.component.materialcolorspalette.store.MaterialColorsPaletteStore.Intent
import dev.t1r.themebuilder.component.materialcolorspalette.store.MaterialColorsPaletteStore.Label
import dev.t1r.themebuilder.component.materialcolorspalette.store.MaterialColorsPaletteStore.State
import dev.t1r.themebuilder.entity.colors.ColorGroup
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum

interface MaterialColorsPaletteStore : Store<Intent, State, Label> {
    data class State(
        val themeColorsModel: ThemeColors = ThemeColors(),
        val materialColors: List<ColorGroup> = emptyList(),
        val themeColorToChange: ThemeColorToChange? = null,
        val newTextColor: String? = null,
        val isPaletteListShowing: Boolean = false,
        val paletteList: List<ThemeColors> = emptyList(),
    )

    data class ThemeColorToChange(
        val marker: ThemeColorsEnum,
        val previousColor: Long,
        val oppositeColor: Long,
    )

    sealed interface Action {
        data class UpdateThemeColors(val model: ThemeColors) : Action
        data class UpdateMaterialColors(val list: List<ColorGroup>) : Action
        data class UpdatePaletteList(val list: List<ThemeColors>) : Action
    }

    sealed interface Intent {
        data class SelectThemeColorToChange(val color: ThemeColorsEnum) : Intent
        data class SelectColorCandidate(val themeColor: ThemeColorsEnum, val color: Long) : Intent
        data object ChangeThemeMode : Intent
        data object CancelSelectColor : Intent
        data object ConfirmSelectedColor : Intent
        data class ChangeTextColor(val themeColor: ThemeColorsEnum, val text: String) : Intent
        data object OpenPaletteList : Intent
        data object ClosePaletteList : Intent
        data object AddPalette : Intent
        data class SelectPalette(val id: Long) : Intent
        data class DeletePalette(val id: Long) : Intent
    }

    sealed interface Label {
        data object Error : Label
    }
}