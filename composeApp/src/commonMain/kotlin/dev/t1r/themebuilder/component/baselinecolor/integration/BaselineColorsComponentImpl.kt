package dev.t1r.themebuilder.component.baselinecolor.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dev.t1r.themebuilder.component.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.component.baselinecolor.BaselineColorsComponent.Model
import dev.t1r.themebuilder.component.baselinecolor.store.BaselineColorsStoreProvider
import dev.t1r.themebuilder.repository.colors.theme.ThemeColorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BaselineColorsComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    params: BaselineColorsComponent.Params,
    themeColorsRepository: ThemeColorsRepository,
) : BaselineColorsComponent, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore {
        BaselineColorsStoreProvider(
            storeFactory = storeFactory,
            themeColorsRepository = themeColorsRepository,
        ).provide()
    }

    override val models: Flow<Model> = store.states.map { stateToModel(it) }
    override val navigationModel = params.navigationModel
}