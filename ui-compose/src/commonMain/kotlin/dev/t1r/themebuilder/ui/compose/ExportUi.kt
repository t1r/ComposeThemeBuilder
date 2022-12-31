package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import dev.t1r.themebuilder.feature.export.ExportComponent
import dev.t1r.themebuilder.ui.compose.common.ScreenContainerWidget

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExportContent(
    component: ExportComponent,
    modifier: Modifier = Modifier,
) {
    val clip = LocalClipboardManager.current

//    clip.setText()
    ScreenContainerWidget(
        navigationModel = component.navigationModel,
        title = "Input Forms",
        content = { pv ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(pv)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = "TEST",
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    ),
                )
            }
        },
        modifier = modifier,
    )
}
