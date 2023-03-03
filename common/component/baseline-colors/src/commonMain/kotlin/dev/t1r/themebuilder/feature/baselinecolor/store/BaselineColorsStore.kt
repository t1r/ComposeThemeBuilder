package dev.t1r.themebuilder.feature.baselinecolor.store

import com.arkivanov.mvikotlin.core.store.Store
import dev.t1r.themebuilder.entity.colors.ThemeColors
import  dev.t1r.themebuilder.feature.baselinecolor.store.BaselineColorsStore.*

interface BaselineColorsStore : Store<Intent, State, Label> {
    data class State(
        val model: ThemeColors = ThemeColors(),
    )

    sealed class Action {
        data class UpdateColors(val model: ThemeColors) : Action()
    }

    sealed class Intent
    sealed class Label
}