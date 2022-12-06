package dev.t1r.themebuilder.feature.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        object ColorsFlow : Child()
    }
}