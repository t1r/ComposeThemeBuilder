package dev.t1r.themebuilder.feature.materialcolorspallet.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dev.t1r.themebuilder.data.colors.material.MaterialColorsRepository
import dev.t1r.themebuilder.data.colors.theme.ThemeColorsRepository
import dev.t1r.themebuilder.entity.colors.ColorModel
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent.Model
import dev.t1r.themebuilder.feature.materialcolorspallet.store.MaterialColorsPalletStore.Intent
import dev.t1r.themebuilder.feature.materialcolorspallet.store.MaterialColorsPalletStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MaterialColorsPalletComponentImpl constructor(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    themeColorsDataSource: ThemeColorsRepository,
    materialColorsDataSource: MaterialColorsRepository,
) : MaterialColorsPalletComponent, ComponentContext by componentContext {
    private val store = MaterialColorsPalletStoreProvider(
        storeFactory = storeFactory,
        themeColorsDataSource = themeColorsDataSource,
        materialColorsDataSource = materialColorsDataSource,
    ).provide()

    override val models: Flow<Model> = store.states.map { stateToModel(it) }

    override fun onThemeColorToChangeSelected(color: ThemeColorsEnum) {
        store.accept(Intent.SelectThemeColorToChange(color))
    }

    override fun onColorSelected(themeColor: ThemeColorsEnum, color: ColorModel) {
        store.accept(Intent.ChangeThemeColor(themeColor, color.color))
    }
}