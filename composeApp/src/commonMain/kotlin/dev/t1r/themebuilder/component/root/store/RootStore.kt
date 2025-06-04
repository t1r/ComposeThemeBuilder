package dev.t1r.themebuilder.component.root.store

import com.arkivanov.mvikotlin.core.store.Store
import dev.t1r.themebuilder.component.root.store.RootStore.Intent
import dev.t1r.themebuilder.component.root.store.RootStore.Label
import dev.t1r.themebuilder.component.root.store.RootStore.State
import dev.t1r.themebuilder.entity.colors.ThemeColors

interface RootStore : Store<Intent, State, Label> {
    data class State(
        val model: ThemeColors = ThemeColors(),
    )

    sealed interface Action {
        data class UpdateColors(val model: ThemeColors) : Action
    }

    sealed interface Intent
    sealed interface Label
}