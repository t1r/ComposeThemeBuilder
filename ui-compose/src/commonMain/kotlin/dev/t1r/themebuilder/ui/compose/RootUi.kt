package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
import dev.t1r.themebuilder.feature.root.RootComponent
import dev.t1r.themebuilder.feature.root.RootComponent.Child
import dev.t1r.themebuilder.feature.root.RootComponent.Model
import dev.t1r.themebuilder.ui.compose.materialcolorspallet.MaterialColorsPalletContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalDecomposeApi::class)
@Composable
fun RootContent(
    component: RootComponent,
    materialColorsPalletComponent: MaterialColorsPalletComponent,
) {
    val model by component.models.collectAsState(Model())
    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        skipHalfExpanded = false,
    )
    val coroutineScope = rememberCoroutineScope()

    DefaultAppTheme(
        primaryColor = model.colors.primary,
        primaryVariantColor = model.colors.primaryVariant,
        secondaryColor = model.colors.secondary,
        secondaryVariantColor = model.colors.secondaryVariant,
        backgroundColor = model.colors.background,
        surfaceColor = model.colors.surface,
        errorColor = model.colors.error,
        onPrimaryColor = model.colors.onPrimary,
        onSecondaryColor = model.colors.onSecondary,
        onBackgroundColor = model.colors.onBackground,
        onSurfaceColor = model.colors.onSurface,
        onErrorColor = model.colors.onError,
    ) {
        ModalBottomSheetLayout(
            modifier = Modifier.fillMaxSize(),
            sheetState = bottomSheetState,
            sheetContent = {
                MaterialColorsPalletContent(
                    component = materialColorsPalletComponent,
                    modifier = Modifier.fillMaxWidth(),
                )
            },
            content = {
                Box(modifier = Modifier.fillMaxSize()) {
                    Children(
                        modifier = Modifier.fillMaxSize(),
                        stack = component.childStack,
                    ) {
                        when (val child = it.instance) {
                            is Child.BaselineColors -> BaselineColorsContent(
                                component = child.component,
                                modifier = Modifier.fillMaxSize(),
                            )

                            is Child.InputForms -> InputFormsContent(
                                navigationModel = child.model,
                                modifier = Modifier.fillMaxSize(),
                            )

                            is Child.Export -> ExportContent(
                                component = child.component,
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    }
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
}