package dev.t1r.themebuilder.ui.compose.common

sealed class Os {
    object Android : Os()
    object Desktop : Os()
    object iOs : Os()
}