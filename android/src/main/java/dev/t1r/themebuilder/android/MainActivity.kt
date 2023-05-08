package dev.t1r.themebuilder.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import dev.t1r.themebuilder.component.materialcolorspalette.integration.MaterialColorsPaletteComponentImpl
import dev.t1r.themebuilder.component.root.integration.RootComponentImpl
import dev.t1r.themebuilder.data.ThemeBuilderDb
import dev.t1r.themebuilder.data.colors.material.MaterialColorsDataSource
import dev.t1r.themebuilder.data.colors.material.MaterialColorsRepositoryImpl
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsDataSource
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsRepositoryImpl
import dev.t1r.themebuilder.data.db.DriverFactory
import dev.t1r.themebuilder.data.kvs.SettingsFactory
import dev.t1r.themebuilder.data.platform.PlatformRepositoryImpl
import dev.t1r.themebuilder.repository.colors.theme.ThemeColorsRepository
import dev.t1r.themebuilder.ui.compose.RootContent
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val loggingStoreFactory get() = LoggingStoreFactory(TimeTravelStoreFactory())
    private val themeColorsRepository: ThemeColorsRepository
        get() = ThemeColorsRepositoryImpl(
            dataSource = ThemeColorsDataSource(),
            db = ThemeBuilderDb(
                driver = DriverFactory(this).create(),
            ),
            settings = SettingsFactory(getPreferences(Context.MODE_PRIVATE)).createSettings(),
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val defaultComponentContext = defaultComponentContext()
            RootContent(
                component = RootComponentImpl(
                    componentContext = defaultComponentContext,
                    storeFactory = loggingStoreFactory,
                    themeColorsRepository = themeColorsRepository,
                    platformRepository = PlatformRepositoryImpl(
                        shareAction = { text -> share(text = text) },
                    ),
                ),
                materialColorsPaletteComponent = MaterialColorsPaletteComponentImpl(
                    componentContext = defaultComponentContext,
                    storeFactory = loggingStoreFactory,
                    themeColorsRepository = themeColorsRepository,
                    materialColorsRepository = MaterialColorsRepositoryImpl(MaterialColorsDataSource()),
                ),
            )
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                themeColorsRepository.themeColorsState()
                    .collect { setStatusBarColor(it.primaryVariant) }
            }
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

    private fun share(
        text: String,
    ) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, null))
    }
}