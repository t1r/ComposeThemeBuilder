package dev.t1r.themebuilder.feature.export.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsRepository
import dev.t1r.themebuilder.feature.export.ExportComponent
import dev.t1r.themebuilder.feature.export.ExportComponent.Model
import dev.t1r.themebuilder.feature.export.store.ExportStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExportComponentImpl constructor(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    params: ExportComponent.Params,
    themeColorsRepository: ThemeColorsRepository,
) : ExportComponent, ComponentContext by componentContext {
    private val store = ExportStoreProvider(
        storeFactory = storeFactory,
        themeColorsRepository = themeColorsRepository,
    ).provide()

    override val models: Flow<Model> = store.states.map { stateToModel(it) }
    override val navigationModel = params.navigationModel
}