package dev.t1r.themebuilder.feature.materialcolorspalette.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.t1r.themebuilder.entity.colors.*
import dev.t1r.themebuilder.feature.materialcolorspalette.store.MaterialColorsPaletteStore.*
import dev.t1r.themebuilder.repository.colors.material.MaterialColorsRepository
import dev.t1r.themebuilder.repository.colors.theme.ThemeColorsRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class MaterialColorsPaletteStoreProvider constructor(
    private val storeFactory: StoreFactory,
    private val themeColorsRepository: ThemeColorsRepository,
    private val materialColorsRepository: MaterialColorsRepository,
) {

    fun provide(): MaterialColorsPaletteStore =
        object : MaterialColorsPaletteStore, Store<Intent, State, Label> by storeFactory.create(
            name = "MaterialColorsPaletteStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(themeColorsRepository, materialColorsRepository),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl,
        ) {}

    private sealed class Message {
        data class UpdateThemeColors(val model: ThemeColors) : Message()
        data class UpdateMaterialColors(val list: List<ColorGroup>) : Message()
        data class SelectThemeColorToChange(val model: ThemeColorToChange?) : Message()
        data class TextColorChange(val text: String) : Message()
        object OpenPaletteList : Message()
        object ClosePaletteList : Message()
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
            is Intent.SelectThemeColorToChange -> resolveSelectThemeColorToChange(getState(), intent.color)
            is Intent.SelectColorCandidate -> resolveSelectColorCandidate(intent)
            is Intent.CancelSelectColor -> resolveCancelSelectColor(getState())
            is Intent.ConfirmSelectedColor -> dispatch(Message.SelectThemeColorToChange(null))
            is Intent.ChangeTextColor -> resolveChangeTextColor(intent)
            is Intent.ChangeThemeMode -> resolveThemeMode(getState())
            is Intent.OpenPaletteList -> dispatch(Message.OpenPaletteList)
            is Intent.ClosePaletteList -> dispatch(Message.ClosePaletteList)
        }

        private fun resolveSelectThemeColorToChange(state: State, marker: ThemeColorsEnum) {
            val previousColor = getColorByThemeColorMarker(marker, state.themeColorsModel)
            val oppositeColor = getOppositeColorByThemeColorMarker(marker, state.themeColorsModel)
            dispatch(
                Message.SelectThemeColorToChange(
                    ThemeColorToChange(
                        marker = marker,
                        previousColor = previousColor,
                        oppositeColor = oppositeColor,
                    )
                )
            )
        }

        private fun resolveSelectColorCandidate(intent: Intent.SelectColorCandidate) {
            try {
                themeColorsRepository.changeThemeColor(intent.themeColor, intent.color)
            } catch (throwable: Throwable) {
                //TODO
            }
        }

        private fun resolveCancelSelectColor(state: State) {
            val model = state.themeColorToChange ?: return
            try {
                themeColorsRepository.changeThemeColor(model.marker, model.previousColor)
                dispatch(Message.SelectThemeColorToChange(null))
            } catch (throwable: Throwable) {
                //TODO
            }
        }

        private fun resolveChangeTextColor(
            intent: Intent.ChangeTextColor,
        ) {
            val newText = intent.text.take(8).filter { it.isDigit() || it.isLetter() }
            try {
                val newColor = newText.toLong(16)
                themeColorsRepository.changeThemeColor(intent.themeColor, newColor)
            } catch (throwable: Throwable) {
                dispatch(Message.TextColorChange(newText))
            }
        }

        private fun resolveThemeMode(state: State) {
            try {
                themeColorsRepository.changeThemeMode(!state.themeColorsModel.isLight)
            } catch (throwable: Throwable) {
                //TODO
            }
        }
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State = when (msg) {
            is Message.UpdateThemeColors -> copy(
                themeColorsModel = msg.model,
                newTextColor = null,
            )
            is Message.UpdateMaterialColors -> copy(
                materialColors = msg.list,
            )
            is Message.SelectThemeColorToChange -> copy(
                themeColorToChange = msg.model,
            )
            is Message.TextColorChange -> copy(
                newTextColor = msg.text,
            )
            is Message.ClosePaletteList -> copy(
                isPaletteListShowing = false,
            )
            is Message.OpenPaletteList -> copy(
                isPaletteListShowing = true,
            )
        }
    }

    private class BootstrapperImpl(
        private val themeColorsRepository: ThemeColorsRepository,
        private val materialColorsRepository: MaterialColorsRepository,
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                themeColorsRepository.themeColorsState()
                    .onEach { dispatch(Action.UpdateThemeColors(it)) }
                    .launchIn(this)
            }
            scope.launch {
                dispatch(Action.UpdateMaterialColors(materialColorsRepository.getMaterialColors()))
            }
        }
    }
}