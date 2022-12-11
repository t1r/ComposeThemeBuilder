package dev.t1r.themebuilder.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import dev.t1r.themebuilder.data.colors.ThemeColorsDataSource
import dev.t1r.themebuilder.data.colors.ThemeColorsRepositoryImpl
import dev.t1r.themebuilder.feature.root.integration.RootComponentImpl
import dev.t1r.themebuilder.ui.compose.RootContent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootContent(
                RootComponentImpl(
                    componentContext = defaultComponentContext(),
                    storeFactory = LoggingStoreFactory(TimeTravelStoreFactory()),
                    colorsDataSource = ThemeColorsRepositoryImpl(ThemeColorsDataSource()),
                )
            )
        }
    }
}