package dev.t1r.themebuilder.ui.compose.export

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun ExportThemeWidget(
    exportString: String,
    isShareShowing: Boolean,
    modifier: Modifier = Modifier,
    onExportButtonClicked: () -> Unit = {},
    onShareButtonClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .weight(1F)
                .verticalScroll(rememberScrollState()),
            text = exportString,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Monospace,
                color = Color.Black,
            ),
        )
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
        ) {
            Button(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .weight(1F),
                content = {
                    Icon(Icons.Filled.ContentCopy, "")
                    Text("Copy")
                },
                onClick = onExportButtonClicked,
            )
            if (isShareShowing) Button(
                modifier = Modifier
                    .padding()
                    .weight(1F),
                content = {
                    Icon(Icons.Filled.Share, "")
                    Text("Share")
                },
                onClick = onShareButtonClicked,
            )
        }
    }
}