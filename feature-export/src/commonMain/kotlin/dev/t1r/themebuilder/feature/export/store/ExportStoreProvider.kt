package dev.t1r.themebuilder.feature.export.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsRepository
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.feature.export.store.ExportStore.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class ExportStoreProvider constructor(
    private val storeFactory: StoreFactory,
    private val themeColorsRepository: ThemeColorsRepository,
) {

    fun provide(): ExportStore = object : ExportStore, Store<Intent, State, Label> by storeFactory.create(
        name = "ExportStore",
        initialState = State(),
        bootstrapper = BootstrapperImpl(themeColorsRepository),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl,
    ) {}

    private sealed class Message {
        data class UpdateExportString(val exportString: String) : Message()
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Message, Label>() {
        override fun executeAction(
            action: Action,
            getState: () -> State,
        ): Unit = when (action) {
            is Action.UpdateColors -> handleUpdateColors(action.model)
        }

        private fun handleUpdateColors(model: ThemeColors) {
            val exportString = """
MaterialTheme(
  colors = Colors(
    primary = ${mapToColorString(model.primary)},
    primaryVariant = ${mapToColorString(model.primaryVariant)},
    secondary = ${mapToColorString(model.secondary)},
    secondaryVariant = ${mapToColorString(model.secondaryVariant)},
    background = ${mapToColorString(model.background)},
    surface = ${mapToColorString(model.surface)},
    error = ${mapToColorString(model.error)},
    onPrimary = ${mapToColorString(model.onPrimary)},
    onSecondary = ${mapToColorString(model.onSecondary)},
    onBackground = ${mapToColorString(model.onBackground)} ,
    onSurface = ${mapToColorString(model.onSurface)},
    onError = ${mapToColorString(model.onError)},
  ),
  content = {},
)
""".trimIndent()
            dispatch(Message.UpdateExportString(exportString))
        }

        private fun mapToColorString(from: Long): String =
            "Color(0x${from.toString(16)})"
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State = when (msg) {
            is Message.UpdateExportString -> copy(
                exportString = msg.exportString,
            )
        }
    }

    private class BootstrapperImpl(
        private val colorsDataSource: ThemeColorsRepository,
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                colorsDataSource.themeColorsState()
                    .onEach { dispatch(Action.UpdateColors(it)) }
                    .launchIn(this)
            }
        }
    }
}