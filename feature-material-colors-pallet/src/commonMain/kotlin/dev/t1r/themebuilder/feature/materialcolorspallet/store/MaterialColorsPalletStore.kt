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
        val themeColorToChange: Pair<ThemeColorsEnum, Long>? = null,
    )

    sealed class Action {
        data class UpdateThemeColors(val model: ThemeColors) : Action()
        data class UpdateMaterialColors(val list: List<ColorGroup>) : Action()
    }

    sealed class Intent {
        data class SelectThemeColorToChange(val color: ThemeColorsEnum) : Intent()
        data class ChangeThemeColor(val themeColor: ThemeColorsEnum, val color: Long) : Intent()
    }

    sealed class Label
}