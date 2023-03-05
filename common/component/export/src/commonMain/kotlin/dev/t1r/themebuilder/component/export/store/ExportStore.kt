package dev.t1r.themebuilder.component.export.store

import com.arkivanov.mvikotlin.core.store.Store
import dev.t1r.themebuilder.component.export.store.ExportStore.*
import dev.t1r.themebuilder.entity.colors.ThemeColors

interface ExportStore : Store<Intent, State, Label> {
    data class State(
        val composeThemeExportString: String = "",
        val androidXmlExportString: String = "",
    )

    sealed class Action {
        data class UpdateColors(val model: ThemeColors) : Action()
    }

    sealed class Intent
    sealed class Label
}