package dev.t1r.themebuilder.ui.compose.materialcolorspalette

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import composethemebuilder.composeapp.generated.resources.Res
import composethemebuilder.composeapp.generated.resources.dark_theme
import composethemebuilder.composeapp.generated.resources.light_theme
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MaterialColorsPaletteModeThemeWidget(
    isLight: Boolean,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit = {},
) {
    Row(
        modifier = modifier.clickable { onClicked() },
    ) {
        Text(
            modifier = Modifier
                .weight(1F)
                .padding(16.dp),
            text = stringResource(
                if (isLight) Res.string.light_theme
                else Res.string.dark_theme
            ),
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                color = Color.Black,
            )
        )
        Box(
            modifier = Modifier
                .weight(1F)
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = if (isLight) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                contentDescription = "",
            )
        }
    }
}