package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
import dev.t1r.themebuilder.feature.root.RootComponent
import dev.t1r.themebuilder.feature.root.RootComponent.Child
import dev.t1r.themebuilder.feature.root.RootComponent.Model

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootContent(
    component: RootComponent,
    materialColorsPalletComponent: MaterialColorsPalletComponent,
) {
    val model by component.models.collectAsState(Model())

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
        Children(
            modifier = Modifier.fillMaxSize(),
            stack = component.childStack,
        ) {
            when (val child = it.instance) {
                is Child.BaselineColors -> BaselineColorsContent(
                    component = child.component,
                    materialColorsPalletComponent = materialColorsPalletComponent,
                    modifier = Modifier.fillMaxSize(),
                )

                is Child.InputForms -> InputFormsContent(
                    navigationModel = child.model,
                    materialColorsPalletComponent = materialColorsPalletComponent,
                    modifier = Modifier.fillMaxSize(),
                )

                is Child.ColorsShowcaseComponents -> ColorsShowcaseComponentsContent(
                    navigationModel = child.model,
                    materialColorsPalletComponent = materialColorsPalletComponent,
                    modifier = Modifier.fillMaxSize(),
                )

                is Child.Export -> ExportContent(
                    component = child.component,
                    modifier = Modifier.fillMaxSize(),
                )

                is Child.About -> AboutContent(
                    navigationModel = child.model,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}