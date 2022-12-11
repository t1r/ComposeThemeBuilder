package dev.t1r.themebuilder.data.colors

import dev.t1r.themebuilder.entity.colors.ThemeColors
import kotlinx.coroutines.flow.*

class ThemeColorsRepositoryImpl(
    dataSource: ThemeColorsDataSource,
) : ThemeColorsRepository {
    private val themeColors = MutableStateFlow(mapThemeColors(dataSource.provideDefaultColors()))
    override fun themeColorsState(): SharedFlow<ThemeColors> = themeColors
}