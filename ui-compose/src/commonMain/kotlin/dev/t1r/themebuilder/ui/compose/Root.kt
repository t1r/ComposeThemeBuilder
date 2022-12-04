package dev.t1r.themebuilder.ui.compose

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.Child
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children

@Composable
fun RootContent(
    component: RootComponent,
) {
    Children(component.routerState) { state ->
        state.instance { child ->
            when (child) {
                is Child.ThemeColors -> ThemeColorsContent(child.component)
            }
        }
    }
}