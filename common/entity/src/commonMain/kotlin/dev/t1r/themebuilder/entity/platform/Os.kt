package dev.t1r.themebuilder.entity.platform

sealed class Os {
    object Android : Os()
    object Desktop : Os()
    object iOs : Os()
}