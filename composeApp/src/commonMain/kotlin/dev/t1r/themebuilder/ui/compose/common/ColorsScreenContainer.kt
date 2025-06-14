package dev.t1r.themebuilder.ui.compose.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.drawer_content_description
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import dev.t1r.themebuilder.ui.compose.appmenu.AppMenuWidget
import dev.t1r.themebuilder.ui.compose.materialcolorspalette.MaterialColorsPaletteContent
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

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
                                    Icon(
                                        Icons.Filled.Menu,
                                        stringResource(Res.string.drawer_content_description)
                                    )
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