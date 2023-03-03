package dev.t1r.themebuilder.ui.compose.materialcolorspalette

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.entity.colors.PaletteThemeColors

@Composable
internal fun MaterialColorsPaletteListContent(
    list: List<PaletteThemeColors>,
    paletteIdToDelete: Long?,
    modifier: Modifier = Modifier,
    onBackToPaletteClicked: () -> Unit = {},
    onAddPaletteClicked: () -> Unit = {},
    onPaletteClicked: (Long) -> Unit = {},
    onDeleteClicked: (Long) -> Unit = {},
    onCancelDeleteClicked: () -> Unit = {},
    onConfirmDeleteClicked: (Long) -> Unit = {},
) {
    Column(modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1F).padding(bottom = 12.dp)) {
            items(list) { item ->
                val model = item.model
                Row(
                    modifier = Modifier
                        .clickable { onPaletteClicked(model.id) }
                        .background(if (item.isSelected) Color.LightGray else Color.Transparent)
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Card(
                        modifier = Modifier.weight(1F),
                        elevation = 4.dp,
                        backgroundColor = Color.Transparent,
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.primary)))
                                Spacer(
                                    modifier = Modifier.weight(1F).aspectRatio(1F)
                                        .background(Color(model.primaryVariant))
                                )
                                Spacer(
                                    modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.secondary))
                                )
                                Spacer(
                                    modifier = Modifier.weight(1F).aspectRatio(1F)
                                        .background(Color(model.secondaryVariant))
                                )
                                Spacer(
                                    modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.background))
                                )
                                Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.surface)))
                                Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.error)))
                                Spacer(
                                    modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.onPrimary))
                                )
                                Spacer(
                                    modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.onSecondary))
                                )
                                Spacer(
                                    modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.onBackground))
                                )
                                Spacer(
                                    modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.onSurface))
                                )
                                Spacer(modifier = Modifier.weight(1F).aspectRatio(1F).background(Color(model.onError)))
                            }
                        },
                    )
                    Icon(
                        modifier = Modifier.padding(start = 16.dp),
                        imageVector = if (model.isLight) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                        contentDescription = "",
                    )
                    IconButton(
                        content = { Icon(Icons.Filled.Delete, "Delete") },
                        onClick = { onDeleteClicked(model.id) },
                    )
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 12.dp)) {
            Button(
                modifier = Modifier.weight(1F).padding(end = 12.dp),
                onClick = onAddPaletteClicked,
                content = { Text(text = "Add palette") },
            )
            Button(
                modifier = Modifier.weight(1F),
                onClick = onBackToPaletteClicked,
                content = { Text(text = "Back to palette") },
            )
        }
    }

    if (paletteIdToDelete != null) DeleteAlert(
        onConfirmClicked = {
            onConfirmDeleteClicked(paletteIdToDelete)
            onCancelDeleteClicked()
        },
        onDismiss = onCancelDeleteClicked,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DeleteAlert(
    onConfirmClicked: () -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Are you sure to delete palette?") },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.weight(1f).padding(end = 16.dp),
                    onClick = onDismiss,
                ) {
                    Text("Cancel")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = onConfirmClicked
                ) {
                    Text("Ok")
                }
            }
        }
    )
}