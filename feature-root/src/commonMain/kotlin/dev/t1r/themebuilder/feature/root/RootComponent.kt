package dev.t1r.themebuilder.feature.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class BaselineColors(val component: BaselineColorsComponent) : Child()
    }
}