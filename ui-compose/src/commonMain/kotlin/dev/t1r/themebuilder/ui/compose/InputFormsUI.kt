package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
                    value = "Text",
                    onValueChange = {},
                )
                TextField(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 16.dp),
                    value = "Text",
                    onValueChange = {},
                )
                RadioButton(
                    selected = false,
                    onClick = {},
                )
                Checkbox(
                    checked = false,
                    onCheckedChange = {},
                )
            }
        },
    )
}