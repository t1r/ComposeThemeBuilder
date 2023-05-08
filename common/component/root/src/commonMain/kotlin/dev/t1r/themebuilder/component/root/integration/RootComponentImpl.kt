package dev.t1r.themebuilder.component.root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dev.t1r.themebuilder.component.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.component.baselinecolor.integration.BaselineColorsComponentImpl
import dev.t1r.themebuilder.component.export.ExportComponent
import dev.t1r.themebuilder.component.export.integration.ExportComponentImpl
import dev.t1r.themebuilder.component.root.RootComponent
import dev.t1r.themebuilder.component.root.RootComponent.Child
import dev.t1r.themebuilder.component.root.RootComponent.Model
import dev.t1r.themebuilder.component.root.store.RootStoreProvider
import dev.t1r.themebuilder.entity.navigation.DrawerNavigationModel
import dev.t1r.themebuilder.repository.colors.theme.ThemeColorsRepository
import dev.t1r.themebuilder.repository.platform.PlatformRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RootComponentImpl internal constructor(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    themeColorsRepository: ThemeColorsRepository,
    private val baselineColor: (ComponentContext, BaselineColorsComponent.Params) -> BaselineColorsComponent,
    private val export: (ComponentContext, ExportComponent.Params) -> ExportComponent,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Configuration>()
    private val stack = childStack(
        initialConfiguration = Configuration.BaselineColor,
        source = navigation,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private val store = instanceKeeper.getStore {
        RootStoreProvider(
            storeFactory = storeFactory,
            themeColorsRepository = themeColorsRepository,
        ).provide()
    }

    override val childStack: Value<ChildStack<*, Child>> = stack
    override val models: Flow<Model> = store.states.map { stateToModel(it) }

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        themeColorsRepository: ThemeColorsRepository,
        platformRepository: PlatformRepository,
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
        export = { childContext, params ->
            ExportComponentImpl(
                componentContext = childContext,
                storeFactory = storeFactory,
                themeColorsRepository = themeColorsRepository,
                params = params,
                platformRepository = platformRepository,
            )
        },
    )

    private fun createChild(
        configuration: Configuration,
        componentContext: ComponentContext,
    ): Child = when (configuration) {
        is Configuration.BaselineColor -> Child.BaselineColors(
            baselineColor(
                componentContext,
                BaselineColorsComponent.Params(getDrawerNavigationModel())
            )
        )

        is Configuration.InputForms -> Child.InputForms(getDrawerNavigationModel())
        is Configuration.ColorsShowcaseComponents -> Child.ColorsShowcaseComponents(
            getDrawerNavigationModel()
        )

        is Configuration.Export -> Child.Export(
            export(
                componentContext,
                ExportComponent.Params(getDrawerNavigationModel())
            )
        )

        is Configuration.About -> Child.About(getDrawerNavigationModel())
    }

    private fun getDrawerNavigationModel(): DrawerNavigationModel = DrawerNavigationModel(
        navigateToBaselineColors = { navigation.replaceCurrent(Configuration.BaselineColor) },
        navigateToInputForms = { navigation.replaceCurrent(Configuration.InputForms) },
        navigateToColorsShowcaseComponents = { navigation.replaceCurrent(Configuration.ColorsShowcaseComponents) },
        navigateToExport = { navigation.replaceCurrent(Configuration.Export) },
        navigateToAbout = { navigation.replaceCurrent(Configuration.About) },
    )

    private sealed class Configuration : Parcelable {
        @Parcelize
        object BaselineColor : Configuration()

        @Parcelize
        object InputForms : Configuration()

        @Parcelize
        object ColorsShowcaseComponents : Configuration()

        @Parcelize
        object Export : Configuration()

        @Parcelize
        object About : Configuration()
    }
}