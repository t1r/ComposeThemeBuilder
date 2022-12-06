package dev.t1r.themebuilder.feature.baselinecolor.integration

import com.arkivanov.decompose.ComponentContext
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorsComponent

class BaselineColorsComponentImpl constructor(
    componentContext: ComponentContext,
) : BaselineColorsComponent, ComponentContext by componentContext {
}