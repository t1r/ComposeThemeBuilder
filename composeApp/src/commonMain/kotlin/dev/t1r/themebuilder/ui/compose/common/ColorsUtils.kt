package dev.t1r.themebuilder.ui.compose.common

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

internal fun getContrastColor(color: Color): Color {
    return if (color.luminance() < 0.33) Color.White
    else Color.Black
}