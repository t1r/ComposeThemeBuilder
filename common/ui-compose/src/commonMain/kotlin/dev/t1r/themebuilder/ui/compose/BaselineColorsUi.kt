package dev.t1r.themebuilder.ui.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent.Model
import dev.t1r.themebuilder.feature.materialcolorspalette.MaterialColorsPaletteComponent
import dev.t1r.themebuilder.ui.compose.common.ColorsScreenContainerWidget

@Composable
internal fun BaselineColorsContent(
    component: BaselineColorsComponent,
    materialColorsPaletteComponent: MaterialColorsPaletteComponent,
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

    ColorsScreenContainerWidget(
        navigationModel = component.navigationModel,
        title = "Theme Colors",
        materialColorsPaletteComponent = materialColorsPaletteComponent,
        modifier = modifier,
        content = { pv ->
            Column(
                modifier = Modifier
                    .padding(pv)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Element(
                    title = "Primary",
                    mainColor = primaryColor,
                    onMainColor = onPrimaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = "Primary Variant",
                    mainColor = primaryVariantColor,
                    onMainColor = onPrimaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = "Secondary",
                    mainColor = secondaryColor,
                    onMainColor = onSecondaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = "Secondary Variant",
                    mainColor = secondaryVariantColor,
                    onMainColor = onSecondaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = "Background",
                    mainColor = backgroundColor,
                    onMainColor = onBackgroundColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = "Surface",
                    mainColor = surfaceColor,
                    onMainColor = onSurfaceColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = "Error",
                    mainColor = errorColor,
                    onMainColor = onErrorColor,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
    )
}

@Composable
private fun Element(
    title: String,
    mainColor: Color,
    onMainColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
            .background(mainColor)
            .padding(16.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.ExtraBold,
                color = onMainColor,
            ),
            textAlign = TextAlign.Center,
        )
        Text(
            text = "Text Thin",
            style = TextStyle(
                fontWeight = FontWeight.Thin,
                color = onMainColor,
            )
        )
        Text(
            text = "Text Light",
            style = TextStyle(
                fontWeight = FontWeight.Light,
                color = onMainColor,
            )
        )
        Text(
            text = "Text Normal",
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                color = onMainColor,
            )
        )
        Text(
            text = "Text Medium",
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                color = onMainColor,
            )
        )
        Text(
            text = "Text Bold",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = onMainColor,
            )
        )
        Box(
            modifier = Modifier
                .background(onMainColor, RoundedCornerShape(24.dp))
                .size(24.dp),
        )
    }
}
