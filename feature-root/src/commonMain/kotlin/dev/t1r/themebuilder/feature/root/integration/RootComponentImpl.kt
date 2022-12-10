package dev.t1r.themebuilder.feature.root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dev.t1r.themebuilder.feature.root.RootComponent
import dev.t1r.themebuilder.feature.root.RootComponent.*
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import dev.t1r.themebuilder.data.colors.ThemeColorsDataSource
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.feature.baselinecolor.integration.BaselineColorsComponentImpl
import dev.t1r.themebuilder.feature.root.store.RootStore
import dev.t1r.themebuilder.feature.root.store.RootStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RootComponentImpl internal constructor(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    colorsDataSource: ThemeColorsDataSource,
    private val baselineColor: (ComponentContext) -> BaselineColorsComponent,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()
    private val stack = childStack(
        initialConfiguration = Configuration.BaselineColor,
        source = navigation,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private val store = RootStoreProvider(
        storeFactory = storeFactory,
        colorsDataSource = colorsDataSource,
    ).provide()

    override val childStack: Value<ChildStack<*, Child>> = stack
    override val models: Flow<Model> = store.states.map { stateToModel(it) }

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        colorsDataSource: ThemeColorsDataSource,
    ) : this(
        componentContext = componentContext,
        storeFactory = storeFactory,
        colorsDataSource = colorsDataSource,
        baselineColor = { childContext ->
            BaselineColorsComponentImpl(
                componentContext = childContext,
                storeFactory = storeFactory,
                colorsDataSource = colorsDataSource,
            )
        },
    )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child = when (configuration) {
        Configuration.BaselineColor -> Child.BaselineColors(
            baselineColor(componentContext)
        )
    }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object BaselineColor : Configuration()
    }
}