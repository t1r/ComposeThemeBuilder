package dev.t1r.themebuilder.ui.compose.materialcolorspallet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.entity.colors.ColorGroup
import dev.t1r.themebuilder.entity.colors.ColorModel
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
import dev.t1r.themebuilder.ui.compose.common.getContrastColor

@Composable
internal fun MaterialColorsPalletSelectedModeContent(
    contentState: MaterialColorsPalletComponent.ContentState.SelectedMode,
    materialColors: List<ColorGroup>,
    onColorCandidateSelected: (themeColor: ThemeColorsEnum, color: ColorModel) -> Unit,
    onCancelSelectClicked: () -> Unit,
    onConfirmSelectedClicked: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth().background(Color.Red)) {
                Box(
                    modifier = Modifier
                        .background(Color(contentState.previousColor))
                        .weight(1F),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        text = "Old ${contentState.model.title}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(contentState.oppositeColor),
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .background(Color(contentState.newColor))
                        .weight(1F),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        text = "New ${contentState.model.title}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(contentState.oppositeColor),
                        )
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1F).padding(start = 16.dp),
                    text = "Confirm new selected ${contentState.model.title} color",
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                )
                TextButton(
                    modifier = Modifier.padding(end = 8.dp),
                    content = {
                        Text(
                            text = "Cancel",
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                            )
                        )
                    },
                    onClick = { onCancelSelectClicked() },
                )
                OutlinedButton(
                    modifier = Modifier.padding(end = 16.dp),
                    content = {
                        Text(
                            text = "Confirm",
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                            )
                        )
                    },
                    onClick = { onConfirmSelectedClicked() },
                )
            }
            Divider(modifier = Modifier.fillMaxWidth())
        }
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1F)) {
            items(
                items = materialColors,
                key = { it.title },
            ) { row ->
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
                                    .clickable(
                                        onClick = { onColorCandidateSelected(contentState.model, item) },
                                    )
                                    .background(Color(item.color))
                                    .padding(16.dp),
                            ) {
                                Text(
                                    text = item.title,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Normal,
                                        color = getContrastColor(Color(item.color)),
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}