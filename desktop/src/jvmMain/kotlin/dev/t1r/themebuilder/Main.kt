package dev.t1r.themebuilder

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import dev.t1r.themebuilder.data.colors.material.MaterialColorsDataSource
import dev.t1r.themebuilder.data.colors.material.MaterialColorsRepositoryImpl
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsDataSource
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsRepositoryImpl
import dev.t1r.themebuilder.feature.materialcolorspallet.integration.MaterialColorsPalletComponentImpl
import dev.t1r.themebuilder.feature.root.integration.RootComponentImpl
import dev.t1r.themebuilder.ui.compose.RootContent

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val defaultComponentContext = DefaultComponentContext(LifecycleRegistry())
        val loggingStoreFactory = LoggingStoreFactory(TimeTravelStoreFactory())
        val themeColorsRepository = ThemeColorsRepositoryImpl(ThemeColorsDataSource())
        RootContent(
            RootComponentImpl(
                componentContext = defaultComponentContext,
                storeFactory = loggingStoreFactory,
                themeColorsRepository = themeColorsRepository,
            ),
            materialColorsPalletComponent = MaterialColorsPalletComponentImpl(
                componentContext = defaultComponentContext,
                storeFactory = loggingStoreFactory,
                themeColorsDataSource = themeColorsRepository,
                materialColorsDataSource = MaterialColorsRepositoryImpl(MaterialColorsDataSource()),
            ),
        )
    }
}
