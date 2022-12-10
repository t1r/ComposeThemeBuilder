package dev.t1r.themebuilder.feature.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent
import kotlinx.coroutines.flow.Flow

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>
    val models: Flow<Model>

    sealed class Child {
        data class BaselineColors(val component: BaselineColorsComponent) : Child()
    }

    data class Model(
        val colors: Colors = Colors(),
    )

    data class Colors(
        val primary: Long = 0xFF6200EE,
        val primaryVariant: Long = 0xFF3700B3,
        val secondary: Long = 0xFF03DAC6,
        val secondaryVariant: Long = 0xFF018786,
        val background: Long = 0xFFFFFFFF,
        val surface: Long = 0xFFFFFFFF,
        val error: Long = 0xFFB00020,
        val onPrimary: Long = 0xFFFFFFFF,
        val onSecondary: Long = 0xFF000000,
        val onBackground: Long = 0xFF000000,
        val onSurface: Long = 0xFF000000,
        val onError: Long = 0xFFFFFFFF,
        val isLight: Boolean = true,
    )
}