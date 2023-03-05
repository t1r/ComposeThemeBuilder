package dev.t1r.themebuilder.component.baselinecolor.integration

import dev.t1r.themebuilder.component.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.component.baselinecolor.store.BaselineColorsStore

internal val stateToModel: BaselineColorsStore.State.() -> BaselineColorsComponent.Model =
    {
        BaselineColorsComponent.Model(
            colors = model,
        )
    }
