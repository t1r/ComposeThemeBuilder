package dev.t1r.themebuilder.feature.root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import dev.t1r.themebuilder.feature.root.RootComponent
import dev.t1r.themebuilder.feature.root.RootComponent.*
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.feature.baselinecolor.integration.BaselineColorsComponentImpl

class RootComponentImpl internal constructor(
    componentContext: ComponentContext,
    private val baselineColor: (ComponentContext) -> BaselineColorsComponent,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()
    private val stack = childStack(
        initialConfiguration = Configuration.BaselineColor,
        source = navigation,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override val childStack: Value<ChildStack<*, Child>> = stack

    constructor(
        componentContext: ComponentContext,
    ) : this(
        componentContext = componentContext,
        baselineColor = { childContext ->
            BaselineColorsComponentImpl(
                componentContext = childContext,
            )
        },
    )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child = when (configuration) {
        Configuration.BaselineColor -> Child.BaselineColor(
            baselineColor(componentContext)
        )
    }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object BaselineColor : Configuration()
    }
}