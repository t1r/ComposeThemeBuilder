package dev.t1r.themebuilder.ui.compose.materialcolorspallet

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
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

    Column(
        modifier = modifier.then(
            Modifier.verticalScroll(rememberScrollState())
                .background(Color.White)
        ),
    ) {
        MaterialColorsPalletElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Primary",
            onColorTitle = "On Primary",
            color = primaryColor,
            onColor = onPrimaryColor,
            onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.Primary) },
            onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnPrimary) },
        )
        MaterialColorsPalletElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Primary Variant",
            onColorTitle = "On Primary Variant",
            color = primaryVariantColor,
            onColor = onPrimaryColor,
            onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.PrimaryVariant) },
            onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnPrimary) },
        )
        MaterialColorsPalletElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Secondary",
            onColorTitle = "On Secondary",
            color = secondaryColor,
            onColor = onSecondaryColor,
            onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.Secondary) },
            onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnSecondary) },
        )
        MaterialColorsPalletElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Secondary Variant",
            onColorTitle = "On Secondary Variant",
            color = secondaryVariantColor,
            onColor = onSecondaryColor,
            onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.SecondaryVariant) },
            onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnSecondary) },
        )
        MaterialColorsPalletElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Background",
            onColorTitle = "On Background",
            color = backgroundColor,
            onColor = onBackgroundColor,
            onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.Background) },
            onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnBackground) },
        )
        MaterialColorsPalletElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Surface",
            onColorTitle = "On Surface",
            color = surfaceColor,
            onColor = onSurfaceColor,
            onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.Surface) },
            onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnSurface) },
        )
        MaterialColorsPalletElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Error",
            onColorTitle = "On Error",
            color = errorColor,
            onColor = onErrorColor,
            onColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.Error) },
            onOnColorClicked = { component.onThemeColorToChangeSelected(ThemeColorsEnum.OnError) },
        )
    }
}