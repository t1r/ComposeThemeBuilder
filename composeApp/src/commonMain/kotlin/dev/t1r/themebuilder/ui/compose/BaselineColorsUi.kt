package dev.t1r.themebuilder.ui.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.background_title
import composethemebuilder.composeapp.generated.resources.error_title
import composethemebuilder.composeapp.generated.resources.primary_title
import composethemebuilder.composeapp.generated.resources.primary_variant_title
import composethemebuilder.composeapp.generated.resources.secondary_title
import composethemebuilder.composeapp.generated.resources.secondary_variant_title
import composethemebuilder.composeapp.generated.resources.surface_title
import composethemebuilder.composeapp.generated.resources.text_bold_title
import composethemebuilder.composeapp.generated.resources.text_light_title
import composethemebuilder.composeapp.generated.resources.text_medium_title
import composethemebuilder.composeapp.generated.resources.text_normal_title
import composethemebuilder.composeapp.generated.resources.text_thin_title
import composethemebuilder.composeapp.generated.resources.theme_colors_title
import dev.t1r.themebuilder.component.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.component.baselinecolor.BaselineColorsComponent.Model
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent
import dev.t1r.themebuilder.ui.compose.common.ColorsScreenContainerWidget
import org.jetbrains.compose.resources.stringResource

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
        title = stringResource(Res.string.theme_colors_title),
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
                    title = stringResource(Res.string.primary_title),
                    mainColor = primaryColor,
                    onMainColor = onPrimaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = stringResource(Res.string.primary_variant_title),
                    mainColor = primaryVariantColor,
                    onMainColor = onPrimaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = stringResource(Res.string.secondary_title),
                    mainColor = secondaryColor,
                    onMainColor = onSecondaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = stringResource(Res.string.secondary_variant_title),
                    mainColor = secondaryVariantColor,
                    onMainColor = onSecondaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = stringResource(Res.string.background_title),
                    mainColor = backgroundColor,
                    onMainColor = onBackgroundColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = stringResource(Res.string.surface_title),
                    mainColor = surfaceColor,
                    onMainColor = onSurfaceColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    title = stringResource(Res.string.error_title),
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
            text = stringResource(Res.string.text_thin_title),
            style = TextStyle(
                fontWeight = FontWeight.Thin,
                color = onMainColor,
            )
        )
        Text(
            text = stringResource(Res.string.text_light_title),
            style = TextStyle(
                fontWeight = FontWeight.Light,
                color = onMainColor,
            )
        )
        Text(
            text = stringResource(Res.string.text_normal_title),
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                color = onMainColor,
            )
        )
        Text(
            text = stringResource(Res.string.text_medium_title),
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                color = onMainColor,
            )
        )
        Text(
            text = stringResource(Res.string.text_bold_title),
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
