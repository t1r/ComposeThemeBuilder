package dev.t1r.themebuilder.feature.baselinecolor

import dev.t1r.themebuilder.entity.colors.ThemeColors
import kotlinx.coroutines.flow.Flow

interface BaselineColorsComponent {
    val models: Flow<Model>

    data class Model(
        val colors: ThemeColors = ThemeColors(),
    )
}