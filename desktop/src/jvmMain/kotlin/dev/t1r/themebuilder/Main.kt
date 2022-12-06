package dev.t1r.themebuilder

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import dev.t1r.themebuilder.feature.root.integration.RootComponentImpl
import dev.t1r.themebuilder.ui.compose.DefaultAppTheme
import dev.t1r.themebuilder.ui.compose.RootContent

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        DefaultAppTheme {
            RootContent(
                RootComponentImpl(DefaultComponentContext(LifecycleRegistry()))
            )
        }
    }
}
