package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.alerts
import composethemebuilder.composeapp.generated.resources.button_component
import composethemebuilder.composeapp.generated.resources.colors_showcase_components_title
import composethemebuilder.composeapp.generated.resources.common_cancel
import composethemebuilder.composeapp.generated.resources.common_create
import composethemebuilder.composeapp.generated.resources.common_hide
import composethemebuilder.composeapp.generated.resources.common_ok
import composethemebuilder.composeapp.generated.resources.common_show
import composethemebuilder.composeapp.generated.resources.csc_tab_1
import composethemebuilder.composeapp.generated.resources.csc_tab_2
import composethemebuilder.composeapp.generated.resources.icon_toggle_button
import composethemebuilder.composeapp.generated.resources.icon_toggle_button_component
import composethemebuilder.composeapp.generated.resources.outlined_button_component
import composethemebuilder.composeapp.generated.resources.progress_bar_alert
import composethemebuilder.composeapp.generated.resources.short_some_text
import composethemebuilder.composeapp.generated.resources.show_alert
import composethemebuilder.composeapp.generated.resources.show_linear_progress_bar
import composethemebuilder.composeapp.generated.resources.show_progress_alert
import composethemebuilder.composeapp.generated.resources.show_snack_bar
import composethemebuilder.composeapp.generated.resources.text_message
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import dev.t1r.themebuilder.ui.compose.common.ColorsScreenContainerWidget
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

@Composable
fun ColorsShowcaseComponentsContent(
    navigationModel: DrawerNavigationModel,
    materialColorsPaletteComponent: MaterialColorsPaletteComponent,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    var isIconToggleChecked by remember { mutableStateOf(false) }
    var tabPosition by remember { mutableStateOf(0) }
    var isAlertDialogShowing by remember { mutableStateOf(false) }
    var isProgressAlertDialogShowing by remember { mutableStateOf(false) }
    var isLinearProgressBarShowing by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }

    ColorsScreenContainerWidget(
        materialColorsPaletteComponent = materialColorsPaletteComponent,
        modifier = modifier,
        title = stringResource(Res.string.colors_showcase_components_title),
        navigationModel = navigationModel,
        snackBarHostState = snackBarHostState,
        content = { pv ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(pv),
            ) {
                TabRow(
                    selectedTabIndex = tabPosition,
                ) {
                    Tab(
                        selected = tabPosition == 0,
                        onClick = { tabPosition = 0 },
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = stringResource(Res.string.csc_tab_1).uppercase(),
                            style = MaterialTheme.typography.caption,
                        )
                    }
                    Tab(
                        selected = tabPosition == 1,
                        onClick = { tabPosition = 1 },
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = stringResource(Res.string.csc_tab_2).uppercase(),
                            style = MaterialTheme.typography.caption,
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1F)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.weight(1F),
                            text = stringResource(Res.string.button_component),
                        )
                        Button(
                            onClick = { isAlertDialogShowing = true },
                            content = {
                                Text(
                                    text = stringResource(Res.string.show_alert),
                                )
                            },
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.weight(1F),
                            text = stringResource(Res.string.outlined_button_component),
                        )
                        OutlinedButton(
                            onClick = { isProgressAlertDialogShowing = true },
                            content = {
                                Text(
                                    text = stringResource(Res.string.show_progress_alert)
                                )
                            },
                        )
                    }
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                            .alpha(if (isLinearProgressBarShowing) 1F else 0F),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.weight(1F),
                            text = stringResource(Res.string.show_linear_progress_bar),
                        )
                        TextButton(
                            onClick = { isLinearProgressBarShowing = !isLinearProgressBarShowing },
                            content = {
                                Text(
                                    text = stringResource(
                                        if (isLinearProgressBarShowing) Res.string.common_hide
                                        else Res.string.common_show
                                    )
                                )
                            },
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.weight(1F),
                            text = stringResource(Res.string.show_snack_bar),
                        )
                        IconButton(
                            content = {
                                Icon(
                                    Icons.Filled.Create,
                                    stringResource(Res.string.common_create)
                                )
                            },
                            onClick = {
                                coroutineScope.launch {
                                    snackBarHostState.showSnackbar(getString(Res.string.text_message))
                                }
                            },
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier.weight(1F),
                            text = stringResource(Res.string.icon_toggle_button_component),
                        )
                        IconToggleButton(
                            checked = isIconToggleChecked,
                            onCheckedChange = { isIconToggleChecked = it },
                            content = {
                                Icon(
                                    imageVector = if (isIconToggleChecked) Icons.Filled.Info else Icons.Outlined.Info,
                                    contentDescription = stringResource(Res.string.icon_toggle_button),
                                )
                            },
                        )
                    }
                }
            }
        },
    )

    if (isAlertDialogShowing) AlertDialog(
        onDismissRequest = { isAlertDialogShowing = false },
        title = { Text(text = stringResource(Res.string.alerts)) },
        text = { Text(stringResource(Res.string.short_some_text)) },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.weight(1f).padding(end = 16.dp),
                    onClick = { isAlertDialogShowing = false }
                ) {
                    Text(stringResource(Res.string.common_cancel))
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { isAlertDialogShowing = false }
                ) {
                    Text(stringResource(Res.string.common_ok))
                }
            }
        }
    )

    if (isProgressAlertDialogShowing) AlertDialog(
        onDismissRequest = { isProgressAlertDialogShowing = false },
        title = { Text(text = stringResource(Res.string.progress_bar_alert)) },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.weight(1f).padding(end = 16.dp),
                    onClick = { isProgressAlertDialogShowing = false }
                ) {
                    Text(stringResource(Res.string.common_cancel))
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { isProgressAlertDialogShowing = false }
                ) {
                    Text(stringResource(Res.string.common_ok))
                }
            }
        }
    )
}