package dev.t1r.themebuilder.feature.export.store

import com.arkivanov.mvikotlin.core.store.Store
import dev.t1r.themebuilder.entity.colors.ThemeColors
import  dev.t1r.themebuilder.feature.export.store.ExportStore.*

interface ExportStore : Store<Intent, State, Label> {
    data class State(
        val exportString: String = "",
    )

    sealed class Action {
        data class UpdateColors(val model: ThemeColors) : Action()
    }

    sealed class Intent
    sealed class Label
}