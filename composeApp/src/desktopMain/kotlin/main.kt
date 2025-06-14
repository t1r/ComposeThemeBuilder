import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.app_name
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
import org.jetbrains.compose.resources.stringResource
import java.util.prefs.Preferences

private val loggingStoreFactory = LoggingStoreFactory(TimeTravelStoreFactory())
private val themeColorsRepository: ThemeColorsRepository = ThemeColorsRepositoryImpl(
    dataSource = ThemeColorsDataSource(),
    db = ThemeBuilderDb(DriverFactory().create()),
    settings = SettingsFactory(Preferences.userRoot().node("userDataPreferences")).createSettings(),
)
private val defaultComponentContext = DefaultComponentContext(LifecycleRegistry())

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        icon = painterResource("drawable/ic_launcher.png"),
    ) {
        RootContent(
            RootComponentImpl(
                componentContext = defaultComponentContext,
                storeFactory = loggingStoreFactory,
                themeColorsRepository = themeColorsRepository,
                platformRepository = PlatformRepositoryImpl(
                    shareAction = null,
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
}