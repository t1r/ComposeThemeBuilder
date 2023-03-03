package dev.t1r.themebuilder.feature.baselinecolor.integration

import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent
import dev.t1r.themebuilder.feature.baselinecolor.store.BaselineColorsStore

internal val stateToModel: BaselineColorsStore.State.() -> BaselineColorsComponent.Model =
    {
        BaselineColorsComponent.Model(
            colors = model,
        )
    }
