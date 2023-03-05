package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.feature.export.ExportComponent
import dev.t1r.themebuilder.feature.export.ExportComponent.Model
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

    ScreenContainerWidget(
        navigationModel = component.navigationModel,
        title = "Export",
        snackBarHostState = snackBarHostState,
        content = { pv ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(pv),
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1F)
                        .verticalScroll(rememberScrollState()),
                    text = model.composeThemeExportString,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black,
                    ),
                )
                Button(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .fillMaxWidth(),
                    content = {
                        Icon(Icons.Filled.ContentCopy, "")
                        Text("Copy")
                    },
                    onClick = {
                        clipboardManager.setText(buildAnnotatedString { append(model.composeThemeExportString) })
                        coroutineScope.launch { snackBarHostState.showSnackbar("Theme copied") }
                    },
                )
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(1F)
                        .verticalScroll(rememberScrollState()),
                    text = model.androidXmlExportString,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.Monospace,
                        color = Color.Black,
                    ),
                )
                Button(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .fillMaxWidth(),
                    content = {
                        Icon(Icons.Filled.ContentCopy, "")
                        Text("Copy")
                    },
                    onClick = {
                        clipboardManager.setText(buildAnnotatedString { append(model.androidXmlExportString) })
                        coroutineScope.launch { snackBarHostState.showSnackbar("Theme copied") }
                    },
                )
            }
        },
        modifier = modifier,
    )
}
