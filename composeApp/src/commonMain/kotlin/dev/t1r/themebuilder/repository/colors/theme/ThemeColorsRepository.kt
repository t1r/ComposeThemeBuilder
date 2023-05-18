package dev.t1r.themebuilder.repository.colors.theme

import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import kotlinx.coroutines.flow.Flow

interface ThemeColorsRepository {
    fun themeColorsState(): Flow<ThemeColors>
    fun palettesListState(): Flow<List<ThemeColors>>
    fun changeThemeColor(themeColor: ThemeColorsEnum, color: Long)
    fun changeThemeMode(isLight: Boolean)
    fun deletePalette(id: Long)
    fun addPalette()
    fun selectPalette(id: Long)
}