package dev.t1r.themebuilder.ui.compose.export

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dev.t1r.themebuilder.component.export.ExportComponent
import dev.t1r.themebuilder.component.export.ExportComponent.Model
import dev.t1r.themebuilder.ui.compose.common.ScreenContainerWidget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
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
    val pagerState = rememberPagerState()

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
                    onClick = {
                        coroutineScope.launch { pagerState.scrollToPage(0) }
                    },
                ) {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Compose".uppercase(),
                        style = MaterialTheme.typography.caption,
                    )
                }
                Tab(
                    selected = tabPosition == 1,
                    onClick = {
                        coroutineScope.launch { pagerState.scrollToPage(1) }
                    },
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
            HorizontalPager(
                modifier = Modifier
                    .padding(pv)
                    .fillMaxSize(),
                count = 2,
                state = pagerState,
            ) { index ->
                when (index) {
                    0 -> ExportThemeWidget(
                        exportString = model.composeThemeExportString,
                        modifier = Modifier.fillMaxSize(),
                        onExportButtonClicked = {
                            copyToClipboardAction(
                                string = model.composeThemeExportString,
                                clipboardManager = clipboardManager,
                                coroutineScope = coroutineScope,
                                snackBarHostState = snackBarHostState
                            )
                        },
                        onShareButtonClicked = {
                            component.onShareClicked(model.composeThemeExportString)
                        },
                    )

                    1 -> ExportThemeWidget(
                        exportString = model.androidXmlExportString,
                        modifier = Modifier.fillMaxSize(),
                        onExportButtonClicked = {
                            copyToClipboardAction(
                                string = model.androidXmlExportString,
                                clipboardManager = clipboardManager,
                                coroutineScope = coroutineScope,
                                snackBarHostState = snackBarHostState
                            )
                        },
                        onShareButtonClicked = {
                            component.onShareClicked(model.androidXmlExportString)
                        },
                    )

                    else -> Spacer(modifier = Modifier.fillMaxSize())
                }
            }
        },
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            tabPosition = page
        }
    }
}

private fun copyToClipboardAction(
    string: String,
    clipboardManager: ClipboardManager,
    coroutineScope: CoroutineScope,
    snackBarHostState: SnackbarHostState,
) {
    clipboardManager.setText(buildAnnotatedString { append(string) })
    coroutineScope.launch { snackBarHostState.showSnackbar("Theme copied") }
}