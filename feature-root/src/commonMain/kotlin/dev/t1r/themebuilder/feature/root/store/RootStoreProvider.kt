package dev.t1r.themebuilder.feature.root.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.t1r.themebuilder.data.colors.ThemeColors
import dev.t1r.themebuilder.data.colors.ThemeColorsDataSource
import  dev.t1r.themebuilder.feature.root.store.RootStore.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class RootStoreProvider constructor(
    private val storeFactory: StoreFactory,
    private val colorsDataSource: ThemeColorsDataSource,
) {

    fun provide(): RootStore = object : RootStore, Store<Intent, State, Label> by storeFactory.create(
        name = "AddNewSymptomStore",
        initialState = State(),
        bootstrapper = BootstrapperImpl(colorsDataSource),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl,
    ) {}

    private sealed class Message {
        data class UpdateColors(val model: ThemeColors) : Message()
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Message, Label>() {
        override fun executeAction(
            action: Action,
            getState: () -> State,
        ): Unit = when (action) {
            is Action.UpdateColors -> dispatch(Message.UpdateColors(action.model))
        }
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State = when (msg) {
            is Message.UpdateColors -> copy(
                model = msg.model,
            )
        }
    }

    private class BootstrapperImpl(
        private val colorsDataSource: ThemeColorsDataSource,
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