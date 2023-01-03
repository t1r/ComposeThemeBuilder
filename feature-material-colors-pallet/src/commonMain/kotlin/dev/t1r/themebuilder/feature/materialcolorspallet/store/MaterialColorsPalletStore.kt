package dev.t1r.themebuilder.feature.materialcolorspallet.store

import com.arkivanov.mvikotlin.core.store.Store
import dev.t1r.themebuilder.entity.colors.ColorGroup
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import dev.t1r.themebuilder.feature.materialcolorspallet.store.MaterialColorsPalletStore.*

interface MaterialColorsPalletStore : Store<Intent, State, Label> {
    data class State(
        val themeColorsModel: ThemeColors = ThemeColors(),
        val materialColors: List<ColorGroup> = emptyList(),
        val themeColorToChange: ThemeColorToChange? = null,
        val newTextColor: String? = null,
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
        object CancelSelectColor : Intent()
        object ConfirmSelectedColor : Intent()
        data class ChangeTextColor(val themeColor: ThemeColorsEnum, val text: String) : Intent()
    }

    sealed class Label
}