package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@Composable
fun ExportContent(
    component: ExportComponent,
    modifier: Modifier = Modifier,
) {
    val model by component.models.collectAsState(Model())
    val clipboardManager = LocalClipboardManager.current

    ScreenContainerWidget(
        navigationModel = component.navigationModel,
        title = "Export",
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
                    text = model.exportString,
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

                        Icon(Icons.Filled.Share, "")
                        Text("Copy")
                    },
                    onClick = {
                        clipboardManager.setText(
                            buildAnnotatedString { append(model.exportString) }
                        )
                    },
                )
            }
        },
        modifier = modifier,
    )
}
