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
    themeColorsRepository: ThemeColorsRepository,
) : BaselineColorsComponent, ComponentContext by componentContext {
    private val store = BaselineColorsStoreProvider(
        storeFactory = storeFactory,
        colorsDataSource = themeColorsRepository,
    ).provide()

    override val models: Flow<Model> = store.states.map { stateToModel(it) }
}