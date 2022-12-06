package dev.t1r.themebuilder.feature.baselinecolor.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import dev.t1r.themebuilder.feature.baselinecolor.BaselineColorComponent

class BaselineColorComponentImpl constructor(
    componentContext: ComponentContext,
) : BaselineColorComponent, ComponentContext by componentContext {
}