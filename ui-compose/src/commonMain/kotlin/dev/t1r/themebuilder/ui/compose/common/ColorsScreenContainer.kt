package dev.t1r.themebuilder.ui.compose.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
import dev.t1r.themebuilder.ui.compose.materialcolorspallet.MaterialColorsPalletContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColorsScreenContainerWidget(
    materialColorsPalletComponent: MaterialColorsPalletComponent,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        skipHalfExpanded = false,
    )
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = bottomSheetState,
        sheetContent = {
            MaterialColorsPalletContent(
                component = materialColorsPalletComponent,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                content()
                FloatingActionButton(
                    onClick = { coroutineScope.launch { bottomSheetState.show() } },
                    modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp)
                ) {
                    Icon(Icons.Filled.Edit, "")
                }
            }
        }
    )
}