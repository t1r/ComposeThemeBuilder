package dev.t1r.themebuilder.ui.compose.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import dev.t1r.themebuilder.ui.compose.appmenu.AppMenuWidget
import kotlinx.coroutines.launch

@Composable
internal fun ScreenContainerWidget(
    navigationModel: DrawerNavigationModel,
    title: String,
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState, snackBarHostState)
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
        bottomBar = bottomBar,
        content = content,
    )
}