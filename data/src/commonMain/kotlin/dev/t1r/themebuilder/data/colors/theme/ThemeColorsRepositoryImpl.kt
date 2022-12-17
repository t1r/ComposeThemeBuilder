package dev.t1r.themebuilder.data.colors.theme

import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update

class ThemeColorsRepositoryImpl(
    dataSource: ThemeColorsDataSource,
) : ThemeColorsRepository {
    private val themeColors = MutableStateFlow(mapThemeColors(dataSource.provideDefaultColors()))
    override fun themeColorsState(): SharedFlow<ThemeColors> = themeColors

    override fun changeThemeColor(themeColor: ThemeColorsEnum, color: Long) {
        themeColors.update { state ->
            when (themeColor) {
                ThemeColorsEnum.Background -> state.copy(
                    background = color,
                )

                ThemeColorsEnum.Error -> state.copy(
                    error = color,
                )

                ThemeColorsEnum.OnBackground -> state.copy(
                    onBackground = color,
                )

                ThemeColorsEnum.OnError -> state.copy(
                    onError = color,
                )

                ThemeColorsEnum.OnPrimary -> state.copy(
                    onPrimary = color,
                )

                ThemeColorsEnum.OnSecondary -> state.copy(
                    onSecondary = color,
                )

                ThemeColorsEnum.OnSurface -> state.copy(
                    onSurface = color,
                )

                ThemeColorsEnum.Primary -> state.copy(
                    primary = color,
                )

                ThemeColorsEnum.PrimaryVariant -> state.copy(
                    primaryVariant = color,
                )

                ThemeColorsEnum.Secondary -> state.copy(
                    secondary = color,
                )

                ThemeColorsEnum.SecondaryVariant -> state.copy(
                    secondaryVariant = color,
                )

                ThemeColorsEnum.Surface -> state.copy(
                    surface = color,
                )
            }
        }
    }
}