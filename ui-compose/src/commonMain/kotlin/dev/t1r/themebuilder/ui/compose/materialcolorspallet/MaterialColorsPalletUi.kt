package dev.t1r.themebuilder.ui.compose.materialcolorspallet

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent.ContentState
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent.Model

@Composable
fun MaterialColorsPalletContent(
    component: MaterialColorsPalletComponent,
    modifier: Modifier = Modifier,
) {
    val model by component.models.collectAsState(Model())

    val primaryColor by animateColorAsState(Color(model.colors.primary))
    val primaryVariantColor by animateColorAsState(Color(model.colors.primaryVariant))
    val secondaryColor by animateColorAsState(Color(model.colors.secondary))
    val secondaryVariantColor by animateColorAsState(Color(model.colors.secondaryVariant))
    val backgroundColor by animateColorAsState(Color(model.colors.background))
    val surfaceColor by animateColorAsState(Color(model.colors.surface))
    val errorColor by animateColorAsState(Color(model.colors.error))

    val onPrimaryColor by animateColorAsState(Color(model.colors.onPrimary))
    val onSecondaryColor by animateColorAsState(Color(model.colors.onSecondary))
    val onBackgroundColor by animateColorAsState(Color(model.colors.onBackground))
    val onSurfaceColor by animateColorAsState(Color(model.colors.onSurface))
    val onErrorColor by animateColorAsState(Color(model.colors.onError))

    Box(
        modifier = modifier.then(
            Modifier.background(Color.White)
        ),
    ) {
        when (val contentState = model.contentState) {
            is ContentState.Normal -> MaterialColorsPalletNormalContent(
                modifier = Modifier.fillMaxWidth(),
                primaryColor = primaryColor,
                primaryVariantColor = primaryVariantColor,
                secondaryColor = secondaryColor,
                secondaryVariantColor = secondaryVariantColor,
                backgroundColor = backgroundColor,
                surfaceColor = surfaceColor,
                errorColor = errorColor,
                onPrimaryColor = onPrimaryColor,
                onSecondaryColor = onSecondaryColor,
                onBackgroundColor = onBackgroundColor,
                onSurfaceColor = onSurfaceColor,
                onErrorColor = onErrorColor,
                isLight = model.colors.isLight,
                onThemeColorToChangeSelected = component::onThemeColorToChangeSelected,
                onChangeThemeModeClicked = component::onChangeThemeModeClicked,
            )

            is ContentState.SelectedMode -> MaterialColorsPalletSelectedModeContent(
                contentState = contentState,
                materialColors = model.materialColors,
                onColorCandidateSelected = component::onColorCandidateSelected,
                onCancelSelectClicked = component::onCancelSelectClicked,
                onConfirmSelectedClicked = component::onConfirmSelectedClicked,
                onTextColorChanged = component::onTextColorChanged,
            )
        }
    }
}