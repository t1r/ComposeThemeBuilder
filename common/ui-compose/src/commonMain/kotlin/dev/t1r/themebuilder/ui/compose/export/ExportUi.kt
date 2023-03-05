package dev.t1r.themebuilder.ui.compose.export

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.component.export.ExportComponent
import dev.t1r.themebuilder.component.export.ExportComponent.Model
import dev.t1r.themebuilder.ui.compose.common.ScreenContainerWidget
import kotlinx.coroutines.launch

@Composable
fun ExportContent(
    component: ExportComponent,
    modifier: Modifier = Modifier,
) {
    val model by component.models.collectAsState(Model())
    val clipboardManager = LocalClipboardManager.current
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    var tabPosition by remember { mutableStateOf(0) }

    ScreenContainerWidget(
        modifier = modifier,
        navigationModel = component.navigationModel,
        title = "Export",
        snackBarHostState = snackBarHostState,
        bottomBar = {
            TabRow(
                selectedTabIndex = tabPosition,
            ) {
                Tab(
                    selected = tabPosition == 0,
                    onClick = { tabPosition = 0 },
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Compose".uppercase(),
                        style = MaterialTheme.typography.caption,
                    )
                }
                Tab(
                    selected = tabPosition == 1,
                    onClick = { tabPosition = 1 },
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Android XML".uppercase(),
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        },
        content = { pv ->
            Crossfade(
                modifier = Modifier.fillMaxSize().padding(pv),
                targetState = tabPosition,
            ) {
                when (tabPosition) {
                    0 -> ExportThemeWidget(
                        exportString = model.composeThemeExportString,
                        modifier = Modifier.fillMaxSize(),
                        onButtonClicked = {
                            clipboardManager.setText(buildAnnotatedString { append(model.composeThemeExportString) })
                            coroutineScope.launch { snackBarHostState.showSnackbar("Theme copied") }
                        }
                    )

                    1 -> ExportThemeWidget(
                        exportString = model.androidXmlExportString,
                        modifier = Modifier.fillMaxSize(),
                        onButtonClicked = {
                            clipboardManager.setText(buildAnnotatedString { append(model.androidXmlExportString) })
                            coroutineScope.launch { snackBarHostState.showSnackbar("Theme copied") }
                        }
                    )

                    else -> Spacer(modifier = Modifier.fillMaxSize())
                }
            }
        },
    )
}
