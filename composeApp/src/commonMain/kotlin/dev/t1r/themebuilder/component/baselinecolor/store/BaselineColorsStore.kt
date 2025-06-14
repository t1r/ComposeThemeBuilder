package dev.t1r.themebuilder.component.baselinecolor.store

import com.arkivanov.mvikotlin.core.store.Store
import dev.t1r.themebuilder.component.baselinecolor.store.BaselineColorsStore.Intent
import dev.t1r.themebuilder.component.baselinecolor.store.BaselineColorsStore.Label
import dev.t1r.themebuilder.component.baselinecolor.store.BaselineColorsStore.State
import dev.t1r.themebuilder.entity.colors.ThemeColors

interface BaselineColorsStore : Store<Intent, State, Label> {
    data class State(
        val model: ThemeColors = ThemeColors(),
    )

    sealed interface Action {
        data class UpdateColors(val model: ThemeColors) : Action
    }

    sealed interface Intent
    sealed interface Label
}