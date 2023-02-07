package dev.t1r.themebuilder.feature.baselinecolor.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.feature.baselinecolor.store.BaselineColorsStore.*
import dev.t1r.themebuilder.repository.colors.theme.ThemeColorsRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class BaselineColorsStoreProvider constructor(
    private val storeFactory: StoreFactory,
    private val colorsDataSource: ThemeColorsRepository,
) {

    fun provide(): BaselineColorsStore =
        object : BaselineColorsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "BaselineColorsStore",
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