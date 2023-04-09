package dev.t1r.themebuilder.component.export.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dev.t1r.themebuilder.component.export.ExportComponent
import dev.t1r.themebuilder.component.export.ExportComponent.Model
import dev.t1r.themebuilder.component.export.store.ExportStoreProvider
import dev.t1r.themebuilder.repository.colors.theme.ThemeColorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExportComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    params: ExportComponent.Params,
    themeColorsRepository: ThemeColorsRepository,
    private val shareAction: ((String) -> Unit)?,
) : ExportComponent, ComponentContext by componentContext {
    private val store = ExportStoreProvider(
        storeFactory = storeFactory,
        themeColorsRepository = themeColorsRepository,
    ).provide()

    override val models: Flow<Model> = store.states.map { stateToModel(it) }
    override val navigationModel = params.navigationModel

    override fun onShareClicked(text: String) {
        shareAction?.invoke(text)
    }
}