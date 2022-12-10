package dev.t1r.themebuilder.data.colors

import kotlinx.coroutines.flow.StateFlow

interface ThemeColorsDataSource {
    fun themeColorsState(): StateFlow<ThemeColorsModel>
}