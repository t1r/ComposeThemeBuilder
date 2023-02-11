package dev.t1r.themebuilder.ui.compose.materialcolorspalette

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.t1r.themebuilder.feature.materialcolorspalette.MaterialColorsPaletteComponent
import dev.t1r.themebuilder.feature.materialcolorspalette.MaterialColorsPaletteComponent.ContentState
import dev.t1r.themebuilder.feature.materialcolorspalette.MaterialColorsPaletteComponent.Model

@Composable
fun MaterialColorsPaletteContent(
    component: MaterialColorsPaletteComponent,
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
            is ContentState.Normal -> MaterialColorsPaletteNormalContent(
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
                onChangePaletteClicked = component::onChangePaletteClicked,
            )

            is ContentState.ChangeColorMode -> MaterialColorsPaletteChangeColorContent(
                modifier = Modifier.fillMaxWidth(),
                contentState = contentState,
                materialColors = model.materialColors,
                onColorCandidateSelected = component::onColorCandidateSelected,
                onCancelSelectClicked = component::onCancelSelectClicked,
                onConfirmSelectedClicked = component::onConfirmSelectedClicked,
                onTextColorChanged = component::onTextColorChanged,
            )
            is ContentState.PaletteList -> MaterialColorsPaletteListContent(
                modifier = Modifier.fillMaxWidth(),
                onBackToPaletteClicked = component::onBackToPaletteClicked,
            )
        }
    }
}