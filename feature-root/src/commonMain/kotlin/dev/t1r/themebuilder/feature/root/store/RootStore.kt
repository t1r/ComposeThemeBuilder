package dev.t1r.themebuilder.feature.root.store

import com.arkivanov.mvikotlin.core.store.Store
import dev.t1r.themebuilder.data.colors.ThemeColorsModel
import  dev.t1r.themebuilder.feature.root.store.RootStore.*

interface RootStore : Store<Intent, State, Label> {
    data class State(
        val model: ThemeColorsModel? = null,
    )

    sealed class Action {
        data class UpdateColors(val model: ThemeColorsModel) : Action()
    }

    sealed class Intent
    sealed class Label
}