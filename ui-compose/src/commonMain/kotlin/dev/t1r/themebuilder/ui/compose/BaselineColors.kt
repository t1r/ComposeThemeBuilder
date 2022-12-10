package dev.t1r.themebuilder.ui.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent

@Composable
internal fun BaselineColorsContent(
    component: BaselineColorsComponent,
    modifier: Modifier = Modifier,
) {
    val primaryColor by animateColorAsState(Color(0xFF6200EE))
    val primaryVariantColor by animateColorAsState(Color(0xFF3700B3))
    val secondaryColor by animateColorAsState(Color(0xFF03DAC6))
    val secondaryVariantColor by animateColorAsState(Color(0xFF018786))
    val backgroundColor by animateColorAsState(Color(0xFFFFFFFF))
    val surfaceColor by animateColorAsState(Color(0xFFFFFFFF))
    val errorColor by animateColorAsState(Color(0xFFB00020))

    val onPrimaryColor by animateColorAsState(Color(0xFFFFFFFF))
    val onSecondaryColor by animateColorAsState(Color(0xFF000000))
    val onBackgroundColor by animateColorAsState(Color(0xFF000000))
    val onSurfaceColor by animateColorAsState(Color(0xFF000000))
    val onErrorColor by animateColorAsState(Color(0xFFFFFFFF))

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar {
                Text("Back")
            }
        },
        content = { pv ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(pv)
                    .verticalScroll(rememberScrollState()),
            ) {
                Element(
                    mainColor = primaryColor,
                    onMainColor = onPrimaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    mainColor = primaryVariantColor,
                    onMainColor = onPrimaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    mainColor = secondaryColor,
                    onMainColor = onSecondaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    mainColor = secondaryVariantColor,
                    onMainColor = onSecondaryColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    mainColor = backgroundColor,
                    onMainColor = onBackgroundColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
                    mainColor = surfaceColor,
                    onMainColor = onSurfaceColor,
                    modifier = Modifier.fillMaxWidth()
                )
                Element(
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