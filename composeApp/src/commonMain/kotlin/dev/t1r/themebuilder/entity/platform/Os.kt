package dev.t1r.themebuilder.entity.platform

sealed interface Os {
    data object Android : Os
    data object Desktop : Os
    data object iOs : Os
}