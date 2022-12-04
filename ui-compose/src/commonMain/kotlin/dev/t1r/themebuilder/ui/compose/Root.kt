package dev.t1r.themebuilder.ui.compose

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
//            is Child.ColorsFlow -> ThemeColorsContent(child.component)
            is Child.ColorsFlow -> Text("TEXT!")
        }
    }
}