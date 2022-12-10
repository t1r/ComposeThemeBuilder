package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import dev.t1r.themebuilder.feature.root.RootComponent
import dev.t1r.themebuilder.feature.root.RootComponent.Child
import dev.t1r.themebuilder.feature.root.RootComponent.*

@Composable
fun RootContent(
    component: RootComponent,
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
                    child.component,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}