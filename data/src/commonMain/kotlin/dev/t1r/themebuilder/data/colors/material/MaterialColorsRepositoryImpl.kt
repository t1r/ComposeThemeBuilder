package dev.t1r.themebuilder.data.colors.material

import dev.t1r.themebuilder.entity.colors.ColorGroup

class MaterialColorsRepositoryImpl(
    private val dataSource: MaterialColorsDataSource,
) : MaterialColorsRepository {

    override fun getMaterialColors(): List<ColorGroup> = dataSource.materialColors()
}