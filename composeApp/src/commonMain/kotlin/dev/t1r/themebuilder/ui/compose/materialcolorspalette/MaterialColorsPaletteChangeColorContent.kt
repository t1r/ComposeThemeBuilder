package dev.t1r.themebuilder.ui.compose.materialcolorspalette

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.common_cancel
import composethemebuilder.composeapp.generated.resources.common_confirm
import composethemebuilder.composeapp.generated.resources.confirm_selected_color_template
import composethemebuilder.composeapp.generated.resources.input_custom_color
import composethemebuilder.composeapp.generated.resources.new_template
import composethemebuilder.composeapp.generated.resources.old_template
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent
import dev.t1r.themebuilder.entity.colors.ColorGroup
import dev.t1r.themebuilder.entity.colors.ColorModel
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import dev.t1r.themebuilder.ui.compose.common.getContrastColor
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MaterialColorsPaletteChangeColorContent(
    contentState: MaterialColorsPaletteComponent.ContentState.ChangeColorMode,
    materialColors: List<ColorGroup>,
    onColorCandidateSelected: (themeColor: ThemeColorsEnum, color: ColorModel) -> Unit,
    onCancelSelectClicked: () -> Unit,
    onConfirmSelectedClicked: () -> Unit,
    onTextColorChanged: (themeColor: ThemeColorsEnum, text: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .background(Color(contentState.previousColor))
                        .weight(1F),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        text = stringResource(Res.string.old_template, contentState.model.title),
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
                        text = stringResource(Res.string.new_template, contentState.model.title),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(contentState.oppositeColor),
                        )
                    )
                }
            }
            Divider(modifier = Modifier.fillMaxWidth())
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1F).padding(end = 16.dp),
                    text = stringResource(Res.string.input_custom_color),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                )
                BasicTextField(
                    modifier = Modifier,
                    value = contentState.newColorText,
                    onValueChange = {
                        onTextColorChanged(contentState.model, it)
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
                )
            }
            Divider(modifier = Modifier.fillMaxWidth())
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1F).padding(start = 16.dp),
                    text = stringResource(
                        Res.string.confirm_selected_color_template,
                        contentState.model.title
                    ),
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                    )
                )
                TextButton(
                    modifier = Modifier.padding(end = 8.dp),
                    content = {
                        Text(
                            text = stringResource(Res.string.common_cancel),
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
                            text = stringResource(Res.string.common_confirm),
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
                                        onClick = {
                                            onColorCandidateSelected(
                                                contentState.model,
                                                item
                                            )
                                        },
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