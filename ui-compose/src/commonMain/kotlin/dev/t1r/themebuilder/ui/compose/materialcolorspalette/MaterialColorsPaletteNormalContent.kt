package dev.t1r.themebuilder.ui.compose.materialcolorspalette

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum

@Composable
internal fun MaterialColorsPaletteNormalContent(
    primaryColor: Color,
    primaryVariantColor: Color,
    secondaryColor: Color,
    secondaryVariantColor: Color,
    backgroundColor: Color,
    surfaceColor: Color,
    errorColor: Color,
    onPrimaryColor: Color,
    onSecondaryColor: Color,
    onBackgroundColor: Color,
    onSurfaceColor: Color,
    onErrorColor: Color,
    isLight: Boolean,
    modifier: Modifier = Modifier,
    onThemeColorToChangeSelected: (ThemeColorsEnum) -> Unit = {},
    onChangeThemeModeClicked: () -> Unit = {},
    onChangePaletteClicked: () -> Unit = {},
) {
    Column(modifier = modifier) {
        MaterialColorsPaletteElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = ThemeColorsEnum.Primary.title,
            onColorTitle = ThemeColorsEnum.OnPrimary.title,
            color = primaryColor,
            onColor = onPrimaryColor,
            onColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.Primary) },
            onOnColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.OnPrimary) },
        )
        MaterialColorsPaletteElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = ThemeColorsEnum.PrimaryVariant.title,
            onColorTitle = ThemeColorsEnum.OnPrimary.title,
            color = primaryVariantColor,
            onColor = onPrimaryColor,
            onColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.PrimaryVariant) },
            onOnColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.OnPrimary) },
        )
        MaterialColorsPaletteElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = ThemeColorsEnum.Secondary.title,
            onColorTitle = ThemeColorsEnum.OnSecondary.title,
            color = secondaryColor,
            onColor = onSecondaryColor,
            onColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.Secondary) },
            onOnColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.OnSecondary) },
        )
        MaterialColorsPaletteElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = ThemeColorsEnum.SecondaryVariant.title,
            onColorTitle = ThemeColorsEnum.OnSecondary.title,
            color = secondaryVariantColor,
            onColor = onSecondaryColor,
            onColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.SecondaryVariant) },
            onOnColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.OnSecondary) },
        )
        MaterialColorsPaletteElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = ThemeColorsEnum.Background.title,
            onColorTitle = ThemeColorsEnum.OnBackground.title,
            color = backgroundColor,
            onColor = onBackgroundColor,
            onColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.Background) },
            onOnColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.OnBackground) },
        )
        MaterialColorsPaletteElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = ThemeColorsEnum.Surface.title,
            onColorTitle = ThemeColorsEnum.OnSurface.title,
            color = surfaceColor,
            onColor = onSurfaceColor,
            onColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.Surface) },
            onOnColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.OnSurface) },
        )
        MaterialColorsPaletteElementWidget(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = ThemeColorsEnum.Error.title,
            onColorTitle = ThemeColorsEnum.OnError.title,
            color = errorColor,
            onColor = onErrorColor,
            onColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.Error) },
            onOnColorClicked = { onThemeColorToChangeSelected(ThemeColorsEnum.OnError) },
        )
        MaterialColorsPaletteModeThemeWidget(
            isLight = isLight,
            modifier = Modifier.fillMaxWidth(),
            onClicked = onChangeThemeModeClicked,
        )
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
            onClick = onChangePaletteClicked,
            content = {
                Text(
                    text = "Change palette",
                )
            },
        )
    }
}