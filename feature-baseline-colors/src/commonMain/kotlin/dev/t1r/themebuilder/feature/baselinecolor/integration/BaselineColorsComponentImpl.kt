package dev.t1r.themebuilder.feature.baselinecolor.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsRepository
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent.Model
import dev.t1r.themebuilder.feature.baselinecolor.store.BaselineColorsStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BaselineColorsComponentImpl constructor(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    colorsDataSource: ThemeColorsRepository,
) : BaselineColorsComponent, ComponentContext by componentContext {
    private val store = BaselineColorsStoreProvider(
        storeFactory = storeFactory,
        colorsDataSource = colorsDataSource,
    ).provide()

    override val models: Flow<Model> = store.states.map { stateToModel(it) }
}