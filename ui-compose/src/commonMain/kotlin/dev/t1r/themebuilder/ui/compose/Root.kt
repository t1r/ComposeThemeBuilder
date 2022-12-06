package dev.t1r.themebuilder.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import dev.t1r.themebuilder.feature.root.RootComponent
import dev.t1r.themebuilder.feature.root.RootComponent.Child

@Composable
fun RootContent(
    component: RootComponent,
) {
    Children(
        stack = component.childStack,
    ) {
        when (val child = it.instance) {
            is Child.BaselineColors -> BaselineColorsContent(
                child.component,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}