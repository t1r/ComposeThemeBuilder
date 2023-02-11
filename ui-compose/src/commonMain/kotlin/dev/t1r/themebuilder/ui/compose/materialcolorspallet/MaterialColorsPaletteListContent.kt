package dev.t1r.themebuilder.ui.compose.materialcolorspallet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun MaterialColorsPaletteListContent(
    modifier: Modifier = Modifier,
    onBackToPaletteClicked: () -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        item {
            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                onClick = onBackToPaletteClicked,
                content = {
                    Text(
                        text = "Back to palette",
                    )
                },
            )
        }

    }
}