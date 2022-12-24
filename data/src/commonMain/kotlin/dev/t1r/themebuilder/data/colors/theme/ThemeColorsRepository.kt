package dev.t1r.themebuilder.data.colors.theme

import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import kotlinx.coroutines.flow.SharedFlow

interface ThemeColorsRepository {
    fun themeColorsState(): SharedFlow<ThemeColors>
    fun changeThemeColor(themeColor: ThemeColorsEnum, color: Long)
}