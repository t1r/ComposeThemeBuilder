package dev.t1r.themebuilder.component.baselinecolor.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.t1r.themebuilder.component.baselinecolor.store.BaselineColorsStore.Action
import dev.t1r.themebuilder.component.baselinecolor.store.BaselineColorsStore.Intent
import dev.t1r.themebuilder.component.baselinecolor.store.BaselineColorsStore.Label
import dev.t1r.themebuilder.component.baselinecolor.store.BaselineColorsStore.State
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.repository.colors.theme.ThemeColorsRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class BaselineColorsStoreProvider(
    private val storeFactory: StoreFactory,
    private val themeColorsRepository: ThemeColorsRepository,
) {

    fun provide(): BaselineColorsStore =
        object : BaselineColorsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "BaselineColorsStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(themeColorsRepository),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl,
        ) {}

    private sealed interface Message {
        data class UpdateColors(val model: ThemeColors) : Message
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Message, Label>() {
        override fun executeAction(action: Action): Unit = when (action) {
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
        private val themeColorsRepository: ThemeColorsRepository,
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                themeColorsRepository.themeColorsState()
                    .onEach { dispatch(Action.UpdateColors(it)) }
                    .launchIn(this)
            }
        }
    }
}