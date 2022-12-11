package dev.t1r.themebuilder.data.colors.theme

import dev.t1r.themebuilder.entity.colors.ThemeColors
import kotlinx.coroutines.flow.SharedFlow

interface ThemeColorsRepository {
    fun themeColorsState(): SharedFlow<ThemeColors>
}