package dev.t1r.themebuilder.feature.root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import dev.t1r.themebuilder.feature.root.RootComponent
import dev.t1r.themebuilder.feature.root.RootComponent.*
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

class RootComponentImpl internal constructor(
    componentContext: ComponentContext,
    todoString: String = "TODO!",
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()
    private val stack = childStack(
        initialConfiguration = Configuration.Main,
        source = navigation,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override val childStack: Value<ChildStack<*, Child>> = stack

    constructor(
        componentContext: ComponentContext,
    ) : this(
        componentContext = componentContext,
        todoString = "TODO!",
    )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child = when (configuration) {
        Configuration.Main -> Child.ColorsFlow
    }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Main : Configuration()
    }
}