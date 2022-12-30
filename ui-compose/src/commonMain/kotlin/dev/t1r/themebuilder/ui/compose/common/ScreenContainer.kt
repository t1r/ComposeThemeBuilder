package dev.t1r.themebuilder.ui.compose.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import dev.t1r.themebuilder.ui.compose.appmenu.AppMenuWidget
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun ScreenContainerWidget(
    navigationModel: DrawerNavigationModel,
    title: String,
    content: @Composable (PaddingValues) -> Unit,
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
                    Text(title)
                },
            )
        },
        content = content,
    )
}