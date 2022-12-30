package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import dev.t1r.themebuilder.ui.compose.appmenu.AppMenuWidget
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputFormsContent(
    navigationModel: DrawerNavigationModel,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState)
    val keyboardController = LocalSoftwareKeyboardController.current

    var isRadioButtonSelected by remember { mutableStateOf(false) }
    var isCheckBoxChecked by remember { mutableStateOf(false) }
    var isSwitchChecked by remember { mutableStateOf(false) }
    var outlinedTextFieldText by remember { mutableStateOf("") }
    var textFieldText by remember { mutableStateOf("") }
    var sliderPosition by remember { mutableStateOf(0F) }

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        drawerContent = {
            AppMenuWidget(
                navigationModel = navigationModel,
                onNavigationAction = { coroutineScope.launch { drawerState.close() } },
                modifier = Modifier.fillMaxSize(),
            )
        },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        content = {
                            Icon(Icons.Filled.Menu, "Drawer")
                        },
                        onClick = {
                            keyboardController?.hide()
                            coroutineScope.launch { drawerState.open() }
                        },
                    )
                },
                title = {
                    Text("Input Forms")
                },
            )
        },
        content = { pv ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(pv)
                    .verticalScroll(rememberScrollState()),
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 16.dp),
                    value = outlinedTextFieldText,
                    onValueChange = { outlinedTextFieldText = it },
                    placeholder = { Text("OutlinedTextField") },
                    label = { Text("Label") },
                    trailingIcon = {
                        IconButton(
                            content = { Icon(Icons.Filled.Clear, "Clear") },
                            onClick = { outlinedTextFieldText = "" },
                        )
                    },
                )
                TextField(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 16.dp),
                    value = textFieldText,
                    onValueChange = { textFieldText = it },
                    placeholder = { Text("OutlinedTextField") },
                    label = { Text("Label") },
                    trailingIcon = {
                        IconButton(
                            content = { Icon(Icons.Filled.Clear, "Clear") },
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
                        modifier = Modifier.weight(1F).padding(horizontal = 16.dp),
                        text = "RadioButton",
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
                        text = "Checkbox",
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Switch(
                        modifier = Modifier.padding(start = 2.dp),
                        checked = isSwitchChecked,
                        onCheckedChange = { isSwitchChecked = it },
                    )
                    Text(
                        modifier = Modifier.weight(1F).padding(horizontal = 16.dp),
                        text = "Switch",
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