package dev.t1r.themebuilder.component.baselinecolor.store

import com.arkivanov.mvikotlin.core.store.Store
import dev.t1r.themebuilder.component.baselinecolor.store.BaselineColorsStore.*
import dev.t1r.themebuilder.entity.colors.ThemeColors

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