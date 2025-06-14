package dev.t1r.themebuilder.ui.compose.export

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.about_title
import composethemebuilder.composeapp.generated.resources.android_xml_tab
import composethemebuilder.composeapp.generated.resources.compose_tab
import composethemebuilder.composeapp.generated.resources.export_theme_copied
import dev.t1r.themebuilder.component.export.ExportComponent
import dev.t1r.themebuilder.component.export.ExportComponent.Model
import dev.t1r.themebuilder.ui.compose.common.ScreenContainerWidget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

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
    val pagerState = rememberPagerState { 2 }

    ScreenContainerWidget(
        modifier = modifier,
        navigationModel = component.navigationModel,
        title = stringResource(Res.string.about_title),
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
                        text = stringResource(Res.string.compose_tab).uppercase(),
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
                        text = stringResource(Res.string.android_xml_tab).uppercase(),
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
                state = pagerState,
            ) { index ->
                when (index) {
                    0 -> ExportThemeWidget(
                        exportString = model.composeThemeExportString,
                        isShareShowing = model.isShareShowing,
                        modifier = Modifier.fillMaxSize(),
                        onExportButtonClicked = {
                            copyToClipboardAction(
                                string = model.composeThemeExportString,
                                clipboard = clipboardManager,
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
                        isShareShowing = model.isShareShowing,
                        modifier = Modifier.fillMaxSize(),
                        onExportButtonClicked = {
                            copyToClipboardAction(
                                string = model.androidXmlExportString,
                                clipboard = clipboardManager,
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
    clipboard: ClipboardManager,
    coroutineScope: CoroutineScope,
    snackBarHostState: SnackbarHostState,
) {
    clipboard.setText(buildAnnotatedString { append(string) })
    coroutineScope.launch {
        snackBarHostState.showSnackbar(
            getString(Res.string.export_theme_copied)
        )
    }
}