package dev.t1r.themebuilder.component.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dev.t1r.themebuilder.component.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.component.export.ExportComponent
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import kotlinx.coroutines.flow.Flow

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>
    val models: Flow<Model>

    sealed class Child {
        data class BaselineColors(val component: BaselineColorsComponent) : Child()
        data class InputForms(val model: DrawerNavigationModel) : Child()
        data class ColorsShowcaseComponents(val model: DrawerNavigationModel) : Child()
        data class Export(val component: ExportComponent) : Child()
        data class About(val model: DrawerNavigationModel) : Child()
    }

    data class Model(
        val colors: ThemeColors = ThemeColors(),
    )
}