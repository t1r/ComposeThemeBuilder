package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun DefaultAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
//        colors = if (isSystemInDarkTheme()) Res.Color.darks else Res.Color.lights,
        content = content
    )
}