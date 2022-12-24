package dev.t1r.themebuilder.feature.baselinecolor

import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.navigation.DrawerNavigation
import kotlinx.coroutines.flow.Flow

interface BaselineColorsComponent {
    val models: Flow<Model>
    val navigationModel: DrawerNavigation

    data class Model(
        val colors: ThemeColors = ThemeColors(),
    )

    data class Params(
        val navigationModel: DrawerNavigation,
    )
}