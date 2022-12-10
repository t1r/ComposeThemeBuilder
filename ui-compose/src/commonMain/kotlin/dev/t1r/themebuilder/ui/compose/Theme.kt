package dev.t1r.themebuilder.ui.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

@Composable
fun DefaultAppTheme(
    primaryColor: Long,
    primaryVariantColor: Long,
    secondaryColor: Long,
    secondaryVariantColor: Long,
    backgroundColor: Long,
    surfaceColor: Long,
    errorColor: Long,
    onPrimaryColor: Long,
    onSecondaryColor: Long,
    onBackgroundColor: Long,
    onSurfaceColor: Long,
    onErrorColor: Long,
    content: @Composable () -> Unit,
) {
    val primary by animateColorAsState(Color(primaryColor))
    val primaryVariant by animateColorAsState(Color(primaryVariantColor))
    val secondary by animateColorAsState(Color(secondaryColor))
    val secondaryVariant by animateColorAsState(Color(secondaryVariantColor))
    val background by animateColorAsState(Color(backgroundColor))
    val surface by animateColorAsState(Color(surfaceColor))
    val error by animateColorAsState(Color(errorColor))

    val onPrimary by animateColorAsState(Color(onPrimaryColor))
    val onSecondary by animateColorAsState(Color(onSecondaryColor))
    val onBackground by animateColorAsState(Color(onBackgroundColor))
    val onSurface by animateColorAsState(Color(onSurfaceColor))
    val onError by animateColorAsState(Color(onErrorColor))

    MaterialTheme(
        colors = Colors(
            primary = primary,
            primaryVariant = primaryVariant,
            secondary = secondary,
            secondaryVariant = secondaryVariant,
            background = background,
            surface = surface,
            error = error,
            onPrimary = onPrimary,
            onSecondary = onSecondary,
            onBackground = onBackground,
            onSurface = onSurface,
            onError = onError,
            isLight = isSystemInDarkTheme(),
        ),
        content = content,
    )
}