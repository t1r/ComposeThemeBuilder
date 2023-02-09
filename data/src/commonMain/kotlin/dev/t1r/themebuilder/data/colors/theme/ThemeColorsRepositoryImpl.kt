package dev.t1r.themebuilder.data.colors.theme

import app.cash.sqldelight.coroutines.asFlow
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getLongFlow
import dev.t1r.themebuilder.data.ThemeBuilderDb
import dev.t1r.themebuilder.entity.colors.ThemeColors
import dev.t1r.themebuilder.entity.colors.ThemeColorsEnum
import dev.t1r.themebuilder.repository.colors.theme.ThemeColorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class ThemeColorsRepositoryImpl(
    dataSource: ThemeColorsDataSource,
    private val db: ThemeBuilderDb,
    private val settings: ObservableSettings,
) : ThemeColorsRepository {

    init {
        if (settings.getLongOrNull(THEME_PALETTE_KEY) == null) {
            settings.putLong(THEME_PALETTE_KEY, 1)
        }
        if (db.themePaletteQueries.selectByIndex(1).executeAsOneOrNull() == null) {
            val from = dataSource.provideDefaultColors()
            db.themePaletteQueries.insertRow(
                primaryColor = from.primary,
                primaryVariant = from.primaryVariant,
                secondary = from.secondary,
                secondaryVariant = from.secondaryVariant,
                background = from.background,
                surface = from.surface,
                error = from.error,
                onPrimary = from.onPrimary,
                onSecondary = from.onSecondary,
                onBackground = from.onBackground,
                onSurface = from.onSurface,
                onError = from.onError,
                isLight = from.isLight,
            )
        }
    }

    override fun themeColorsState(): Flow<ThemeColors> {
        return settings
            .getLongFlow(THEME_PALETTE_KEY, 1)
            .flatMapLatest {
                db.themePaletteQueries.selectByIndex(it).asFlow()
            }
            .map { mapDbToThemeColors(it.executeAsOne()) }
    }

    override fun changeThemeColor(themeColor: ThemeColorsEnum, color: Long) {
        val key = settings.getLongOrNull(THEME_PALETTE_KEY) ?: throw RuntimeException()
        when (themeColor) {
            ThemeColorsEnum.Background -> db.themePaletteQueries.updateBackgroundColor(
                color = color,
                uid = key,
            )

            ThemeColorsEnum.Error -> db.themePaletteQueries.updateErrorColor(
                color = color,
                uid = key,
            )

            ThemeColorsEnum.OnBackground -> db.themePaletteQueries.updateOnBackgroundColor(
                color = color,
                uid = key,
            )

            ThemeColorsEnum.OnError -> db.themePaletteQueries.updateOnErrorColor(
                color = color,
                uid = key,
            )

            ThemeColorsEnum.OnPrimary -> db.themePaletteQueries.updateOnPrimaryColor(
                color = color,
                uid = key,
            )

            ThemeColorsEnum.OnSecondary -> db.themePaletteQueries.updateOnSecondaryColor(
                color = color,
                uid = key,
            )

            ThemeColorsEnum.OnSurface -> db.themePaletteQueries.updateOnSurfaceColor(
                color = color,
                uid = key,
            )

            ThemeColorsEnum.Primary -> db.themePaletteQueries.updatePrimaryColor(
                color = color,
                uid = key,
            )

            ThemeColorsEnum.PrimaryVariant -> db.themePaletteQueries.updatePrimaryVariantColor(
                color = color,
                uid = key,
            )

            ThemeColorsEnum.Secondary -> db.themePaletteQueries.updateSecondaryColor(
                color = color,
                uid = key,
            )

            ThemeColorsEnum.SecondaryVariant -> db.themePaletteQueries.updateSecondaryVariantColor(
                color = color,
                uid = key,
            )

            ThemeColorsEnum.Surface -> db.themePaletteQueries.updateSurfaceColor(
                color = color,
                uid = key,
            )
        }
    }

    override fun changeThemeMode(isLight: Boolean) {
        val key = settings.getLongOrNull(THEME_PALETTE_KEY) ?: throw RuntimeException()
        db.themePaletteQueries.updateIsLightColor(
            isLight = isLight,
            uid = key,
        )
    }

    companion object {
        private const val THEME_PALETTE_KEY = "THEME_PALETTE_KEY"
    }
}