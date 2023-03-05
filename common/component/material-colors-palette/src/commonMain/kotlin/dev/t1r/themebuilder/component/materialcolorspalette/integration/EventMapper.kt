package dev.t1r.themebuilder.component.materialcolorspalette.integration

import  dev.t1r.themebuilder.component.materialcolorspalette.MaterialColorsPaletteComponent.Event
import dev.t1r.themebuilder.component.materialcolorspalette.store.MaterialColorsPaletteStore

internal val labelToEvent: MaterialColorsPaletteStore.Label.() -> Event = {
    when (this) {
        is MaterialColorsPaletteStore.Label.Error -> Event.Error
    }
}