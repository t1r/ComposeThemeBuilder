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
    private val dataSource: ThemeColorsDataSource,
    private val db: ThemeBuilderDb,
    private val settings: ObservableSettings,
) : ThemeColorsRepository {

    init {
        if (settings.getLongOrNull(THEME_PALETTE_KEY) == null) {
            settings.putLong(THEME_PALETTE_KEY, THEME_PALETTE_DEFAULT_KEY)
        }
        if (db.themePaletteQueries.selectByIndex(THEME_PALETTE_DEFAULT_KEY).executeAsOneOrNull() == null) {
            addPalette()
        }
    }

    override fun themeColorsState(): Flow<ThemeColors> {
        return settings
            .getLongFlow(THEME_PALETTE_KEY, THEME_PALETTE_DEFAULT_KEY)
            .flatMapLatest {
                db.themePaletteQueries.selectByIndex(it).asFlow()
            }
            .map { mapDbToThemeColors(it.executeAsOne()) }
    }

    override fun palettesListState(): Flow<List<ThemeColors>> {
        return db.themePaletteQueries.selectAll().asFlow()
            .map { query -> query.executeAsList().map(::mapDbToThemeColors) }
    }

    override fun changeThemeColor(themeColor: ThemeColorsEnum, color: Long) {
        val key = settings.getLongOrNull(THEME_PALETTE_KEY) ?: throw RuntimeException()
        when (themeColor) {
            is ThemeColorsEnum.Background -> db.themePaletteQueries.updateBackgroundColor(
                color = color,
                uid = key,
            )

            is ThemeColorsEnum.Error -> db.themePaletteQueries.updateErrorColor(
                color = color,
                uid = key,
            )

            is ThemeColorsEnum.OnBackground -> db.themePaletteQueries.updateOnBackgroundColor(
                color = color,
                uid = key,
            )

            is ThemeColorsEnum.OnError -> db.themePaletteQueries.updateOnErrorColor(
                color = color,
                uid = key,
            )

            is ThemeColorsEnum.OnPrimary -> db.themePaletteQueries.updateOnPrimaryColor(
                color = color,
                uid = key,
            )

            is ThemeColorsEnum.OnSecondary -> db.themePaletteQueries.updateOnSecondaryColor(
                color = color,
                uid = key,
            )

            is ThemeColorsEnum.OnSurface -> db.themePaletteQueries.updateOnSurfaceColor(
                color = color,
                uid = key,
            )

            is ThemeColorsEnum.Primary -> db.themePaletteQueries.updatePrimaryColor(
                color = color,
                uid = key,
            )

            is ThemeColorsEnum.PrimaryVariant -> db.themePaletteQueries.updatePrimaryVariantColor(
                color = color,
                uid = key,
            )

            is ThemeColorsEnum.Secondary -> db.themePaletteQueries.updateSecondaryColor(
                color = color,
                uid = key,
            )

            is ThemeColorsEnum.SecondaryVariant -> db.themePaletteQueries.updateSecondaryVariantColor(
                color = color,
                uid = key,
            )

            is ThemeColorsEnum.Surface -> db.themePaletteQueries.updateSurfaceColor(
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

    override fun deletePalette(id: Long) {
        val key = settings.getLongOrNull(THEME_PALETTE_KEY) ?: throw RuntimeException()
        if (id == THEME_PALETTE_DEFAULT_KEY || key == id) return
        db.themePaletteQueries.deleteRowById(
            uid = key
        )
    }

    override fun addPalette() {
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

    override fun selectPalette(id: Long) {
        settings.putLong(THEME_PALETTE_KEY, id)
    }

    companion object {
        private const val THEME_PALETTE_KEY = "THEME_PALETTE_KEY"
        private const val THEME_PALETTE_DEFAULT_KEY = 1L
    }
}