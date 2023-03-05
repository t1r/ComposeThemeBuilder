package dev.t1r.themebuilder.feature.materialcolorspalette.integration

import  dev.t1r.themebuilder.feature.materialcolorspalette.MaterialColorsPaletteComponent.Event
import dev.t1r.themebuilder.feature.materialcolorspalette.store.MaterialColorsPaletteStore

internal val labelToEvent: MaterialColorsPaletteStore.Label.() -> Event = {
    when (this) {
        is MaterialColorsPaletteStore.Label.Error -> Event.Error
    }
}