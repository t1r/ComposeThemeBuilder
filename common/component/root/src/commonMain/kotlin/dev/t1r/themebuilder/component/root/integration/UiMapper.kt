package dev.t1r.themebuilder.component.root.integration

import dev.t1r.themebuilder.component.root.RootComponent
import dev.t1r.themebuilder.component.root.store.RootStore

internal val stateToModel: RootStore.State.() -> RootComponent.Model =
    {
        RootComponent.Model(
            colors = model,
        )
    }