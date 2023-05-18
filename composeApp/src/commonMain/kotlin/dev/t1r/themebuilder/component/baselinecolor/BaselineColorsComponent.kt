package dev.t1r.themebuilder.component.baselinecolor

import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import kotlinx.coroutines.flow.Flow

interface BaselineColorsComponent {
    val models: Flow<Model>
    val navigationModel: DrawerNavigationModel

    data class Model(
        val colors: ThemeColors = ThemeColors(),
    )

    data class Params(
        val navigationModel: DrawerNavigationModel,
    )
}