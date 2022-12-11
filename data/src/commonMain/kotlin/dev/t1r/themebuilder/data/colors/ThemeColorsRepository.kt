package dev.t1r.themebuilder.data.colors

import dev.t1r.themebuilder.entity.colors.ThemeColors
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface ThemeColorsRepository {
    fun themeColorsState(): SharedFlow<ThemeColors>
}