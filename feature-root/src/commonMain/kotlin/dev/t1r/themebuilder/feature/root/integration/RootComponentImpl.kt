package dev.t1r.themebuilder.feature.root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsRepository
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.feature.baselinecolor.integration.BaselineColorsComponentImpl
import dev.t1r.themebuilder.feature.root.RootComponent
import dev.t1r.themebuilder.feature.root.RootComponent.Child
import dev.t1r.themebuilder.feature.root.RootComponent.Model
import dev.t1r.themebuilder.feature.root.store.RootStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RootComponentImpl internal constructor(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    themeColorsRepository: ThemeColorsRepository,
    private val baselineColor: (ComponentContext, BaselineColorsComponent.Params) -> BaselineColorsComponent,
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
        themeColorsRepository = themeColorsRepository,
    ).provide()

    override val childStack: Value<ChildStack<*, Child>> = stack
    override val models: Flow<Model> = store.states.map { stateToModel(it) }

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        themeColorsRepository: ThemeColorsRepository,
    ) : this(
        componentContext = componentContext,
        storeFactory = storeFactory,
        themeColorsRepository = themeColorsRepository,
        baselineColor = { childContext, params ->
            BaselineColorsComponentImpl(
                componentContext = childContext,
                storeFactory = storeFactory,
                themeColorsRepository = themeColorsRepository,
                params = params,
            )
        },
    )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child = when (configuration) {
        Configuration.BaselineColor -> Child.BaselineColors(
            baselineColor(
                componentContext,
                BaselineColorsComponent.Params(getDrawerNavigationModel())
            )
        )

        Configuration.InputForms -> Child.InputForms(getDrawerNavigationModel())
    }

    private fun getDrawerNavigationModel(): DrawerNavigationModel = DrawerNavigationModel(
        navigateToBaselineColors = { navigation.replaceCurrent(Configuration.BaselineColor) },
        navigateToInputForms = { navigation.replaceCurrent(Configuration.InputForms) },
    )

    private sealed class Configuration : Parcelable {
        @Parcelize
        object BaselineColor : Configuration()

        @Parcelize
        object InputForms : Configuration()
    }
}