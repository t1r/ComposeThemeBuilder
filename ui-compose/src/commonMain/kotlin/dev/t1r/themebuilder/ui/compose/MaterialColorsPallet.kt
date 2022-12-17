package dev.t1r.themebuilder.ui.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
        Element(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Primary",
            onColorTitle = "On Primary",
            color = primaryColor,
            onColor = onPrimaryColor,
            onColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.Primary) },
            onOnColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.OnPrimary) },
        )
        Element(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Primary Variant",
            onColorTitle = "On Primary Variant",
            color = primaryVariantColor,
            onColor = onPrimaryColor,
            onColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.PrimaryVariant) },
            onOnColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.OnPrimary) },
        )
        Element(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Secondary",
            onColorTitle = "On Secondary",
            color = secondaryColor,
            onColor = onSecondaryColor,
            onColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.Secondary) },
            onOnColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.OnSecondary) },
        )
        Element(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Secondary Variant",
            onColorTitle = "On Secondary Variant",
            color = secondaryVariantColor,
            onColor = onSecondaryColor,
            onColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.SecondaryVariant) },
            onOnColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.OnSecondary) },
        )
        Element(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Background",
            onColorTitle = "On Background",
            color = backgroundColor,
            onColor = onBackgroundColor,
            onColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.Background) },
            onOnColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.OnBackground) },
        )
        Element(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Surface",
            onColorTitle = "On Surface",
            color = surfaceColor,
            onColor = onSurfaceColor,
            onColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.Surface) },
            onOnColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.OnSurface) },
        )
        Element(
            modifier = Modifier.fillMaxWidth(),
            colorTitle = "Error",
            onColorTitle = "On Error",
            color = errorColor,
            onColor = onErrorColor,
            onColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.Error) },
            onOnColorClicked = { component.onSelectColorToChange(ThemeColorsEnum.OnError) },
        )
    }
}

@Composable
private fun Element(
    colorTitle: String,
    onColorTitle: String,
    color: Color,
    onColor: Color,
    onColorClicked: () -> Unit,
    onOnColorClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .clickable(onClick = onColorClicked)
                .weight(1F)
                .background(color)
                .padding(16.dp),
        ) {
            Text(
                text = colorTitle,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    color = onColor,
                )
            )
        }
        Box(
            modifier = Modifier.clickable(onClick = onOnColorClicked)
                .weight(1F)
                .background(onColor)
                .padding(16.dp),
        ) {
            Text(
                text = onColorTitle,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    color = color,
                )
            )
        }
    }
}