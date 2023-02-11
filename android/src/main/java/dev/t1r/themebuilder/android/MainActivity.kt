package dev.t1r.themebuilder.android

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.lifecycleScope
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import dev.t1r.themebuilder.data.ThemeBuilderDb
import dev.t1r.themebuilder.data.colors.material.MaterialColorsDataSource
import dev.t1r.themebuilder.data.colors.material.MaterialColorsRepositoryImpl
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsDataSource
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsRepositoryImpl
import dev.t1r.themebuilder.data.db.DriverFactory
import dev.t1r.themebuilder.data.kvs.SettingsFactory
import dev.t1r.themebuilder.feature.materialcolorspalette.integration.MaterialColorsPaletteComponentImpl
import dev.t1r.themebuilder.feature.root.integration.RootComponentImpl
import dev.t1r.themebuilder.ui.compose.RootContent
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loggingStoreFactory = LoggingStoreFactory(TimeTravelStoreFactory())
        val themeColorsRepository = ThemeColorsRepositoryImpl(
            dataSource = ThemeColorsDataSource(),
            db = ThemeBuilderDb(
                driver = DriverFactory(this).create(),
            ),
            settings = SettingsFactory(getPreferences(Context.MODE_PRIVATE)).createSettings(),
        )

        setContent {
            val defaultComponentContext = defaultComponentContext()
            RootContent(
                component = RootComponentImpl(
                    componentContext = defaultComponentContext,
                    storeFactory = loggingStoreFactory,
                    themeColorsRepository = themeColorsRepository,
                ),
                materialColorsPaletteComponent = MaterialColorsPaletteComponentImpl(
                    componentContext = defaultComponentContext,
                    storeFactory = loggingStoreFactory,
                    themeColorsDataSource = themeColorsRepository,
                    materialColorsDataSource = MaterialColorsRepositoryImpl(MaterialColorsDataSource()),
                ),
            )
        }

        lifecycleScope.launchWhenCreated {
            themeColorsRepository.themeColorsState()
                .collectLatest { setStatusBarColor(it.primaryVariant) }
        }
    }

    private fun setStatusBarColor(
        color: Long,
    ) {
        window?.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color(color).toArgb()
        }
    }
}