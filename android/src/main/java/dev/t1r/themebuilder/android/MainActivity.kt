package dev.t1r.themebuilder.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import dev.t1r.themebuilder.data.colors.material.MaterialColorsDataSource
import dev.t1r.themebuilder.data.colors.material.MaterialColorsRepositoryImpl
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsDataSource
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsRepositoryImpl
import dev.t1r.themebuilder.feature.materialcolorspallet.integration.MaterialColorsPalletComponentImpl
import dev.t1r.themebuilder.feature.root.integration.RootComponentImpl
import dev.t1r.themebuilder.ui.compose.RootContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val defaultComponentContext = defaultComponentContext()
            RootContent(
                component = RootComponentImpl(
                    componentContext = defaultComponentContext,
                    storeFactory = LoggingStoreFactory(TimeTravelStoreFactory()),
                    themeColorsRepository = ThemeColorsRepositoryImpl(ThemeColorsDataSource()),
                ),
                materialColorsPalletComponent = MaterialColorsPalletComponentImpl(
                    componentContext = defaultComponentContext,
                    storeFactory = LoggingStoreFactory(TimeTravelStoreFactory()),
                    themeColorsDataSource = ThemeColorsRepositoryImpl(ThemeColorsDataSource()),
                    materialColorsDataSource = MaterialColorsRepositoryImpl(MaterialColorsDataSource()),
                ),
            )
        }
    }
}