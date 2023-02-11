package dev.t1r.themebuilder.ui.compose.materialcolorspalette

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.entity.colors.PaletteThemeColors

@Composable
internal fun MaterialColorsPaletteListContent(
    list: List<PaletteThemeColors>,
    modifier: Modifier = Modifier,
    onBackToPaletteClicked: () -> Unit = {},
    onAddPaletteClicked: () -> Unit = {},
    onPaletteClicked: (Long) -> Unit = {},
    onDeleteClicked: (Long) -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        items(list) { item ->
            val model = item.model
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable { onPaletteClicked(model.id) },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Card(
                    modifier = Modifier.weight(1F),
                    elevation = 8.dp,
                    content = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.primary)))
                            Spacer(
                                modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.primaryVariant))
                            )
                            Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.secondary)))
                            Spacer(
                                modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.secondaryVariant))
                            )
                            Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.background)))
                            Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.surface)))
                            Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.error)))
                            Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.onPrimary)))
                            Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.onSecondary)))
                            Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.onBackground)))
                            Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.onSurface)))
                            Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.onError)))
                        }
                    },
                )
                Icon(
                    modifier = Modifier.padding(start = 16.dp),
                    imageVector = if (model.isLight) Icons.TwoTone.Star else Icons.Filled.Star,
                    contentDescription = "",
                )
                IconButton(
                    content = { Icon(Icons.Filled.Delete, "Delete") },
                    onClick = { onDeleteClicked(model.id) },
                )
            }
        }
        item {
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
                Button(
                    modifier = Modifier.weight(1F).padding(end = 12.dp),
                    onClick = onAddPaletteClicked,
                    content = {
                        Text(
                            text = "Add palette",
                        )
                    },
                )
                Button(
                    modifier = Modifier.weight(1F),
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
}