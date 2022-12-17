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
        RootContent(
            RootComponentImpl(
                componentContext = DefaultComponentContext(LifecycleRegistry()),
                storeFactory = LoggingStoreFactory(TimeTravelStoreFactory()),
                themeColorsRepository = ThemeColorsRepositoryImpl(ThemeColorsDataSource()),
            ),
            materialColorsPalletComponent = MaterialColorsPalletComponentImpl(
                componentContext = DefaultComponentContext(LifecycleRegistry()),
                storeFactory = LoggingStoreFactory(TimeTravelStoreFactory()),
                themeColorsDataSource = ThemeColorsRepositoryImpl(ThemeColorsDataSource()),
                materialColorsDataSource = MaterialColorsRepositoryImpl(MaterialColorsDataSource()),
            ),
        )
    }
}
