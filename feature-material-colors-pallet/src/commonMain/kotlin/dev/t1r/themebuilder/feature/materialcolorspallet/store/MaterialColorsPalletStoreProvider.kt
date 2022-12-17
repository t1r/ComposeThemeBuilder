package dev.t1r.themebuilder.feature.materialcolorspallet.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.t1r.themebuilder.data.colors.material.MaterialColorsRepository
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsRepository
import dev.t1r.themebuilder.entity.colors.ColorGroup
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import dev.t1r.themebuilder.feature.materialcolorspallet.store.MaterialColorsPalletStore.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class MaterialColorsPalletStoreProvider constructor(
    private val storeFactory: StoreFactory,
    private val themeColorsDataSource: ThemeColorsRepository,
    private val materialColorsDataSource: MaterialColorsRepository,
) {

    fun provide(): MaterialColorsPalletStore =
        object : MaterialColorsPalletStore, Store<Intent, State, Label> by storeFactory.create(
            name = "MaterialColorsPalletStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(themeColorsDataSource, materialColorsDataSource),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl,
        ) {}

    private sealed class Message {
        data class UpdateThemeColors(val model: ThemeColors) : Message()
        data class UpdateMaterialColors(val list: List<ColorGroup>) : Message()
        data class SelectThemeColorToChange(val color: ThemeColorsEnum) : Message()
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Message, Label>() {
        override fun executeAction(
            action: Action,
            getState: () -> State,
        ): Unit = when (action) {
            is Action.UpdateThemeColors -> dispatch(Message.UpdateThemeColors(action.model))
            is Action.UpdateMaterialColors -> dispatch(Message.UpdateMaterialColors(action.list))
        }

        override fun executeIntent(
            intent: Intent,
            getState: () -> State,
        ): Unit = when (intent) {
            is Intent.SelectThemeColorToChange -> dispatch(Message.SelectThemeColorToChange(intent.color))
        }
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State = when (msg) {
            is Message.UpdateThemeColors -> copy(
                themeColorsModel = msg.model,
            )

            is Message.UpdateMaterialColors -> copy(
                materialColors = msg.list,
            )

            is Message.SelectThemeColorToChange -> copy(
                themeColorToChange = msg.color,
            )
        }
    }

    private class BootstrapperImpl(
        private val themeColorsDataSource: ThemeColorsRepository,
        private val materialColorsDataSource: MaterialColorsRepository,
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                themeColorsDataSource.themeColorsState()
                    .onEach { dispatch(Action.UpdateThemeColors(it)) }
                    .launchIn(this)
            }
            scope.launch {
                dispatch(Action.UpdateMaterialColors(materialColorsDataSource.getMaterialColors()))
            }
        }
    }
}