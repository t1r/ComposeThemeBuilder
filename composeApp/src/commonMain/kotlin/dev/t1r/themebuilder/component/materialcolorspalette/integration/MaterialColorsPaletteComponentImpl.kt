package dev.t1r.themebuilder.component.materialcolorspalette.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent.Event
import dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent.Model
import dev.t1r.themebuilder.component.materialcolorspalette.store.MaterialColorsPaletteStore.Intent
import dev.t1r.themebuilder.component.materialcolorspalette.store.MaterialColorsPaletteStoreProvider
import dev.t1r.themebuilder.entity.colors.ColorModel
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import dev.t1r.themebuilder.repository.colors.material.MaterialColorsRepository
import dev.t1r.themebuilder.repository.colors.theme.ThemeColorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MaterialColorsPaletteComponentImpl constructor(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    themeColorsRepository: ThemeColorsRepository,
    materialColorsRepository: MaterialColorsRepository,
) : MaterialColorsPaletteComponent, ComponentContext by componentContext {
    private val store = instanceKeeper.getStore {
        MaterialColorsPaletteStoreProvider(
            storeFactory = storeFactory,
            themeColorsRepository = themeColorsRepository,
            materialColorsRepository = materialColorsRepository,
        ).provide()
    }

    override val models: Flow<Model> = store.states.map { stateToModel(it) }
    override val events: Flow<Event> = store.labels.map { labelToEvent(it) }

    override fun onThemeColorToChangeSelected(color: ThemeColorsEnum) {
        store.accept(Intent.SelectThemeColorToChange(color))
    }

    override fun onChangeThemeModeClicked() {
        store.accept(Intent.ChangeThemeMode)
    }

    override fun onColorCandidateSelected(themeColor: ThemeColorsEnum, color: ColorModel) {
        store.accept(Intent.SelectColorCandidate(themeColor, color.color))
    }

    override fun onCancelSelectClicked() {
        store.accept(Intent.CancelSelectColor)
    }

    override fun onConfirmSelectedClicked() {
        store.accept(Intent.ConfirmSelectedColor)
    }

    override fun onTextColorChanged(themeColor: ThemeColorsEnum, text: String) {
        store.accept(Intent.ChangeTextColor(themeColor, text))
    }

    override fun onChangePaletteClicked() {
        store.accept(Intent.OpenPaletteList)
    }

    override fun onBackToPaletteClicked() {
        store.accept(Intent.ClosePaletteList)
    }

    override fun onAddPaletteClicked() {
        store.accept(Intent.AddPalette)
    }

    override fun onPaletteClicked(id: Long) {
        store.accept(Intent.SelectPalette(id))
    }

    override fun onDeleteClicked(id: Long) {
        store.accept(Intent.DeletePalette(id))
    }
}