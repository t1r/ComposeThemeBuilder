package dev.t1r.themebuilder.ui.compose.materialcolorspallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.entity.colors.ColorGroup
import dev.t1r.themebuilder.entity.colors.ColorModel

@Composable
internal fun MaterialPalletWidget(
    materialColors: List<ColorGroup>,
    onColorSelected: (ColorModel) -> Unit,
    columnScope: ColumnScope,
) = columnScope.apply {
    materialColors.forEach { row ->
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                text = row.title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            ) {
                row.items.forEach { item ->
                    Box(
                        modifier = Modifier
                            .clickable(onClick = { onColorSelected(item) })
                            .background(Color(item.color))
                            .padding(16.dp),
                    ) {
                        Text(
                            text = item.title,
                            style = TextStyle(
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                            )
                        )
                    }
                }
            }
        }
    }
}