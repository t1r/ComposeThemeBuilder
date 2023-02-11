package dev.t1r.themebuilder.ui.compose.materialcolorspallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun MaterialColorsPaletteElementWidget(
    colorTitle: String,
    onColorTitle: String,
    color: Color,
    onColor: Color,
    onColorClicked: () -> Unit,
    onOnColorClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .clickable(onClick = onColorClicked)
                .weight(1F)
                .background(color)
                .padding(16.dp),
        ) {
            Text(
                text = colorTitle,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    color = onColor,
                )
            )
        }
        Box(
            modifier = Modifier.clickable(onClick = onOnColorClicked)
                .weight(1F)
                .background(onColor)
                .padding(16.dp),
        ) {
            Text(
                text = onColorTitle,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    color = color,
                )
            )
        }
    }
}