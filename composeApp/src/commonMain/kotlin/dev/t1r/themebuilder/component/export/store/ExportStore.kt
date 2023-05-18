package dev.t1r.themebuilder.component.export.store

import com.arkivanov.mvikotlin.core.store.Store
import dev.t1r.themebuilder.component.export.store.ExportStore.Intent
import dev.t1r.themebuilder.component.export.store.ExportStore.Label
import dev.t1r.themebuilder.component.export.store.ExportStore.State
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.platform.Os

interface ExportStore : Store<Intent, State, Label> {
    data class State(
        val composeThemeExportString: String = "",
        val androidXmlExportString: String = "",
        val isCanShare: Boolean = false,
    )

    sealed class Action {
        data class UpdateColors(val model: ThemeColors) : Action()
        data class HandleOs(val os: Os) : Action()
    }

    sealed class Intent {
        data class Share(val text: String) : Intent()
    }

    sealed class Label
}