package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
import dev.t1r.themebuilder.ui.compose.common.ColorsScreenContainerWidget
import dev.t1r.themebuilder.ui.compose.common.ScreenContainerWidget

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColorsShowcaseComponentsContent(
    navigationModel: DrawerNavigationModel,
    materialColorsPalletComponent: MaterialColorsPalletComponent,
    modifier: Modifier = Modifier,
) {
    var isIconToggleChecked by remember { mutableStateOf(false) }
    var tabPosition by remember { mutableStateOf(0) }
    var isAlertDialogShowing by remember { mutableStateOf(false) }

    ColorsScreenContainerWidget(
        materialColorsPalletComponent = materialColorsPalletComponent,
        modifier = Modifier.fillMaxSize(),
        content = {
            ScreenContainerWidget(
                navigationModel = navigationModel,
                title = "Input Forms",
                content = { pv ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(pv),
                    ) {
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
                                    text = "Button component",
                                )
                                Button(
                                    onClick = { isAlertDialogShowing = true },
                                    content = {
                                        Text(
                                            text = "Show Alert",
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
                                    text = "OutlinedButton component",
                                )
                                OutlinedButton(
                                    onClick = { isAlertDialogShowing = true },
                                    content = {
                                        Text(
                                            text = "Show Alert",
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
                                    text = "TextButton component",
                                )
                                TextButton(
                                    onClick = {},
                                    content = {
                                        Text(
                                            text = "Action",
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
                                    text = "IconButton component",
                                )
                                IconButton(
                                    content = { Icon(Icons.Filled.Create, "Create") },
                                    onClick = { },
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
                                    text = "IconToggleButton component",
                                )
                                IconToggleButton(
                                    checked = isIconToggleChecked,
                                    onCheckedChange = { isIconToggleChecked = it },
                                    content = {
                                        Icon(
                                            imageVector = if (isIconToggleChecked) Icons.Filled.Info else Icons.Outlined.Info,
                                            contentDescription = "IconToggleButton",
                                        )
                                    },
                                )
                            }
                        }
                        TabRow(
                            selectedTabIndex = tabPosition,
                        ) {
                            Tab(
                                selected = tabPosition == 0,
                                onClick = { tabPosition = 0 },
                            ) {
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = "Tab 1".uppercase(),
                                    style = MaterialTheme.typography.caption,
                                )
                            }
                            Tab(
                                selected = tabPosition == 1,
                                onClick = { tabPosition = 1 },
                            ) {
                                Text(
                                    modifier = Modifier.padding(16.dp),
                                    text = "Tab 2".uppercase(),
                                    style = MaterialTheme.typography.caption,
                                )
                            }
                        }
                    }
                },
                modifier = modifier,
            )
        },
    )

    if (isAlertDialogShowing) AlertDialog(
        onDismissRequest = { isAlertDialogShowing = false },
        title = { Text(text = "Alerts Title") },
        text = { Text("Lorem ipsum text") },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.weight(1f).padding(end = 16.dp),
                    onClick = { isAlertDialogShowing = false }
                ) {
                    Text("Cancel")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { isAlertDialogShowing = false }
                ) {
                    Text("Ok")
                }
            }
        }
    )
}