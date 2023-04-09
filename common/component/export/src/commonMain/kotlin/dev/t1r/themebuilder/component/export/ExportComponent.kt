package dev.t1r.themebuilder.component.export

import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import kotlinx.coroutines.flow.Flow

interface ExportComponent {
    val models: Flow<Model>
    val navigationModel: DrawerNavigationModel

    data class Model(
        val composeThemeExportString: String = "",
        val androidXmlExportString: String = "",
    )

    data class Params(
        val navigationModel: DrawerNavigationModel,
    )

    fun onShareClicked(text: String)
}