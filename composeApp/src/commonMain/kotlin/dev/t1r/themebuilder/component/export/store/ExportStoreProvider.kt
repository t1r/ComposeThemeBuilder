package dev.t1r.themebuilder.component.export.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import dev.t1r.themebuilder.component.export.store.ExportStore.Action
import dev.t1r.themebuilder.component.export.store.ExportStore.Intent
import dev.t1r.themebuilder.component.export.store.ExportStore.Label
import dev.t1r.themebuilder.component.export.store.ExportStore.State
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.platform.Os
import dev.t1r.themebuilder.repository.colors.theme.ThemeColorsRepository
import dev.t1r.themebuilder.repository.platform.PlatformRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal class ExportStoreProvider constructor(
    private val storeFactory: StoreFactory,
    private val themeColorsRepository: ThemeColorsRepository,
    private val platformRepository: PlatformRepository,
) {

    fun provide(): ExportStore =
        object : ExportStore, Store<Intent, State, Label> by storeFactory.create(
            name = "ExportStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(themeColorsRepository, platformRepository),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl,
        ) {}

    private sealed class Message {
        data class UpdateComposeThemeExportString(val exportString: String) : Message()
        data class UpdateAndroidXmlExportString(val exportString: String) : Message()
        data class UpdateIsCanShare(val isCanShare: Boolean) : Message()
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Message, Label>() {
        override fun executeAction(
            action: Action,
            getState: () -> State,
        ): Unit = when (action) {
            is Action.UpdateColors -> handleUpdateColors(action.model)
            is Action.HandleOs -> handleOs(action.os)
        }

        private fun handleOs(os: Os) {
            dispatch(Message.UpdateIsCanShare(os is Os.Android || os is Os.iOs))
        }

        override fun executeIntent(
            intent: Intent,
            getState: () -> State,
        ): Unit = when (intent) {
            is Intent.Share -> platformRepository.share(intent.text)
        }

        private fun handleUpdateColors(model: ThemeColors) {
            updateComposeThemeExport(model)
            updateAndroidXmlThemeExport(model)
        }

        private fun updateComposeThemeExport(model: ThemeColors) {
            val exportString = """
MaterialTheme(
  colors = Colors(
    primary = ${mapToComposeColorString(model.primary)},
    primaryVariant = ${mapToComposeColorString(model.primaryVariant)},
    secondary = ${mapToComposeColorString(model.secondary)},
    secondaryVariant = ${mapToComposeColorString(model.secondaryVariant)},
    background = ${mapToComposeColorString(model.background)},
    surface = ${mapToComposeColorString(model.surface)},
    error = ${mapToComposeColorString(model.error)},
    onPrimary = ${mapToComposeColorString(model.onPrimary)},
    onSecondary = ${mapToComposeColorString(model.onSecondary)},
    onBackground = ${mapToComposeColorString(model.onBackground)} ,
    onSurface = ${mapToComposeColorString(model.onSurface)},
    onError = ${mapToComposeColorString(model.onError)},
    isLight = ${model.isLight},
  ),
  content = {},
)
""".trimIndent()
            dispatch(Message.UpdateComposeThemeExportString(exportString))
        }

        private fun updateAndroidXmlThemeExport(model: ThemeColors) {
            val darkModeString = if (model.isLight) "values" else "values-night"
            val exportString = """
<!-- Use "com.google.android.material:material:1.1.0" and higher -->
<!-- Add to file "theme.xml" "$darkModeString" folder -->
<style name="Theme.ApplicationName" parent="Theme.MaterialComponents.NoActionBar">
    <item name="colorPrimary">${mapToAndroidXmlColorString(model.primary)}</item>
    <item name="colorPrimaryVariant">${mapToAndroidXmlColorString(model.primaryVariant)}</item>
    <item name="colorSecondary">${mapToAndroidXmlColorString(model.secondary)}</item>
    <item name="colorSecondaryVariant">${mapToAndroidXmlColorString(model.secondaryVariant)}</item>
    <item name="android:colorBackground">${mapToAndroidXmlColorString(model.background)}</item>
    <item name="colorSurface">${mapToAndroidXmlColorString(model.surface)}</item>
    <item name="colorError">${mapToAndroidXmlColorString(model.error)}</item>
    <item name="colorOnPrimary">${mapToAndroidXmlColorString(model.onPrimary)}</item>
    <item name="colorOnSecondary">${mapToAndroidXmlColorString(model.onSecondary)}</item>
    <item name="colorOnBackground">${mapToAndroidXmlColorString(model.onBackground)} </item>
    <item name="colorOnSurface">${mapToAndroidXmlColorString(model.onSurface)}</item>
    <item name="colorOnError">${mapToAndroidXmlColorString(model.onError)}</item>
    <item name="android:statusBarColor">?attr/colorPrimaryVariant</item>
</style>
""".trimIndent()
            dispatch(Message.UpdateAndroidXmlExportString(exportString))
        }

        private fun mapToComposeColorString(from: Long): String =
            "Color(0x${from.toString(16)})"

        private fun mapToAndroidXmlColorString(from: Long): String =
            "#${from.toString(16)}"
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State = when (msg) {
            is Message.UpdateComposeThemeExportString -> copy(
                composeThemeExportString = msg.exportString,
            )

            is Message.UpdateAndroidXmlExportString -> copy(
                androidXmlExportString = msg.exportString,
            )

            is Message.UpdateIsCanShare -> copy(
                isCanShare = msg.isCanShare,
            )
        }
    }

    private class BootstrapperImpl(
        private val themeColorsRepository: ThemeColorsRepository,
        private val platformRepository: PlatformRepository,
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                themeColorsRepository.themeColorsState()
                    .onEach { dispatch(Action.UpdateColors(it)) }
                    .launchIn(this)
            }
            scope.launch {
                platformRepository.platform()
                    .onEach { dispatch(Action.HandleOs(it)) }
                    .launchIn(this)
            }
        }
    }
}