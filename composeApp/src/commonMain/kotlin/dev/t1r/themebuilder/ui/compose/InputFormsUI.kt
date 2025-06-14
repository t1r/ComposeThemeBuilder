package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Slider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.checkbox
import composethemebuilder.composeapp.generated.resources.common_clear
import composethemebuilder.composeapp.generated.resources.input_forms_title
import composethemebuilder.composeapp.generated.resources.label
import composethemebuilder.composeapp.generated.resources.outlined_text_field
import composethemebuilder.composeapp.generated.resources.radio_button
import composethemebuilder.composeapp.generated.resources.switch
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
                    placeholder = { Text(stringResource(Res.string.outlined_text_field)) },
                    label = { Text(stringResource(Res.string.label)) },
                    trailingIcon = {
                        IconButton(
                            content = {
                                Icon(
                                    Icons.Filled.Clear,
                                    stringResource(Res.string.common_clear)
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
                    placeholder = { Text(stringResource(Res.string.outlined_text_field)) },
                    label = { Text(stringResource(Res.string.label)) },
                    trailingIcon = {
                        IconButton(
                            content = {
                                Icon(
                                    Icons.Filled.Clear,
                                    stringResource(Res.string.common_clear)
                                )
                            },
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
                        text = stringResource(Res.string.radio_button),
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
                        text = stringResource(Res.string.checkbox),
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
                        text = stringResource(Res.string.switch),
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