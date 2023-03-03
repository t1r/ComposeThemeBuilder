package dev.t1r.themebuilder.ui.compose.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import dev.t1r.themebuilder.feature.materialcolorspalette.MaterialColorsPaletteComponent
import dev.t1r.themebuilder.ui.compose.appmenu.AppMenuWidget
import dev.t1r.themebuilder.ui.compose.materialcolorspalette.MaterialColorsPaletteContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ColorsScreenContainerWidget(
    navigationModel: DrawerNavigationModel,
    title: String,
    materialColorsPaletteComponent: MaterialColorsPaletteComponent,
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    content: @Composable (PaddingValues) -> Unit = {},
) {
    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        skipHalfExpanded = false,
    )
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState, snackBarHostState)
    val keyboardController = LocalSoftwareKeyboardController.current

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = bottomSheetState,
        sheetContent = {
            MaterialColorsPaletteContent(
                component = materialColorsPaletteComponent,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        content = {
            Scaffold(
                modifier = modifier,
                scaffoldState = scaffoldState,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { coroutineScope.launch { bottomSheetState.show() } },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(Icons.Filled.Edit, "")
                    }
                },
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
    )
}