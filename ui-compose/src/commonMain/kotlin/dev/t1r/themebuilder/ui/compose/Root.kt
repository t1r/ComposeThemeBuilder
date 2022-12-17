package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
import dev.t1r.themebuilder.feature.root.RootComponent
import dev.t1r.themebuilder.feature.root.RootComponent.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RootContent(
    component: RootComponent,
    materialColorsPalletComponent: MaterialColorsPalletComponent,
) {
    val model by component.models.collectAsState(Model())
    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Expanded,
        skipHalfExpanded = true,
    )

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
                Children(
                    modifier = Modifier.fillMaxSize(),
                    stack = component.childStack,
                ) {
                    when (val child = it.instance) {
                        is Child.BaselineColors -> BaselineColorsContent(
                            child.component,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
        )
    }
}