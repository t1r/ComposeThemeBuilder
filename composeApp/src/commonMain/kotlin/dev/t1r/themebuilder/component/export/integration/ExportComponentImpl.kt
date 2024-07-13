package dev.t1r.themebuilder.component.export.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dev.t1r.themebuilder.component.export.ExportComponent
import dev.t1r.themebuilder.component.export.ExportComponent.Model
import dev.t1r.themebuilder.component.export.store.ExportStore.Intent
import dev.t1r.themebuilder.component.export.store.ExportStoreProvider
import dev.t1r.themebuilder.repository.colors.theme.ThemeColorsRepository
import dev.t1r.themebuilder.repository.platform.PlatformRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ExportComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    params: ExportComponent.Params,
    themeColorsRepository: ThemeColorsRepository,
    platformRepository: PlatformRepository,
) : ExportComponent, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore {
        ExportStoreProvider(
            storeFactory = storeFactory,
            themeColorsRepository = themeColorsRepository,
            platformRepository = platformRepository,
        ).provide()
    }

    override val models: Flow<Model> = store.states.map { stateToModel(it) }
    override val navigationModel = params.navigationModel

    override fun onShareClicked(text: String) {
        store.accept(Intent.Share(text))
    }
}