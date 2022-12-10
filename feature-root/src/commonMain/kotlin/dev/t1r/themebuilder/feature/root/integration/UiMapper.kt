package dev.t1r.themebuilder.feature.root.integration

import dev.t1r.themebuilder.feature.root.RootComponent
import dev.t1r.themebuilder.feature.root.RootComponent.Colors
import dev.t1r.themebuilder.feature.root.store.RootStore

internal val stateToModel: RootStore.State.() -> RootComponent.Model =
    {
        RootComponent.Model(
            colors = Colors(

            )
        )
    }
