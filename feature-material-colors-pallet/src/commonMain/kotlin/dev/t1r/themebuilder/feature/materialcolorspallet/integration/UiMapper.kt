package dev.t1r.themebuilder.feature.materialcolorspallet.integration

import dev.t1r.themebuilder.feature.materialcolorspallet.MaterialColorsPalletComponent
import dev.t1r.themebuilder.feature.materialcolorspallet.store.MaterialColorsPalletStore

internal val stateToModel: MaterialColorsPalletStore.State.() -> MaterialColorsPalletComponent.Model =
    {
        MaterialColorsPalletComponent.Model(
            colors = themeColorsModel,
            materialColors = materialColors,
        )
    }
