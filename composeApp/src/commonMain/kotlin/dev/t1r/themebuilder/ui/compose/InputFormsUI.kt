package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.checkbox_title
import composethemebuilder.composeapp.generated.resources.clear_content_description
import composethemebuilder.composeapp.generated.resources.input_forms_title
import composethemebuilder.composeapp.generated.resources.label_title
import composethemebuilder.composeapp.generated.resources.outlined_text_field_title
import composethemebuilder.composeapp.generated.resources.radio_button_title
import composethemebuilder.composeapp.generated.resources.switch_title
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import dev.t1r.themebuilder.ui.compose.common.ColorsScreenContainerWidget
import org.jetbrains.compose.resources.stringResource

@Composable
fun InputFormsContent(
    navigationModel: DrawerNavigationModel,
    materialColorsPaletteComponent: MaterialColorsPaletteComponent,
    modifier: Modifier = Modifier,
) {
    var isRadioButtonSelected by remember { mutableStateOf(false) }
    var isCheckBoxChecked by remember { mutableStateOf(false) }
    var isSwitchChecked by remember { mutableStateOf(false) }
    var outlinedTextFieldText by remember { mutableStateOf("") }
    var textFieldText by remember { mutableStateOf("") }
    var sliderPosition by remember { mutableStateOf(0F) }

    ColorsScreenContainerWidget(
        navigationModel = navigationModel,
        title = stringResource(Res.string.input_forms_title),
        materialColorsPaletteComponent = materialColorsPaletteComponent,
        modifier = modifier,
        content = { pv ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(pv)
                    .verticalScroll(rememberScrollState()),
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    value = outlinedTextFieldText,
                    onValueChange = { outlinedTextFieldText = it },
                    placeholder = { Text(stringResource(Res.string.outlined_text_field_title)) },
                    label = { Text(stringResource(Res.string.label_title)) },
                    trailingIcon = {
                        IconButton(
                            content = {
                                Icon(
                                    Icons.Filled.Clear,
                                    stringResource(Res.string.clear_content_description)
                                )
                            },
                            onClick = { outlinedTextFieldText = "" },
                        )
                    },
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    value = textFieldText,
                    onValueChange = { textFieldText = it },
                    placeholder = { Text(stringResource(Res.string.outlined_text_field_title)) },
                    label = { Text(stringResource(Res.string.label_title)) },
                    trailingIcon = {
                        IconButton(
                            content = { Icon(Icons.Filled.Clear, stringResource(Res.string.clear_content_description)) },
                            onClick = { textFieldText = "" },
                        )
                    },
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        modifier = Modifier.padding(start = 2.dp),
                        selected = isRadioButtonSelected,
                        onClick = { isRadioButtonSelected = !isRadioButtonSelected },
                    )
                    Text(
                        modifier = Modifier
                            .weight(1F)
                            .padding(horizontal = 16.dp),
                        text = stringResource(Res.string.radio_button_title),
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        modifier = Modifier.padding(start = 2.dp),
                        checked = isCheckBoxChecked,
                        onCheckedChange = { isCheckBoxChecked = it },
                    )
                    Text(
                        modifier = Modifier.weight(1F).padding(horizontal = 16.dp),
                        text = stringResource(Res.string.checkbox_title),
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Switch(
                        modifier = Modifier.padding(start = 10.dp),
                        checked = isSwitchChecked,
                        onCheckedChange = { isSwitchChecked = it },
                    )
                    Text(
                        modifier = Modifier.weight(1F).padding(horizontal = 16.dp),
                        text = stringResource(Res.string.switch_title),
                    )
                }
                Slider(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    value = sliderPosition,
                    onValueChange = { sliderPosition = it },
                )
            }
        },
    )
}