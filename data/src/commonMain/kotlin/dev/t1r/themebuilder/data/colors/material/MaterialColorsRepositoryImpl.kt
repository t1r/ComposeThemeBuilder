package dev.t1r.themebuilder.data.colors.material

import dev.t1r.themebuilder.entity.colors.ColorGroup
import dev.t1r.themebuilder.repository.colors.material.MaterialColorsRepository

class MaterialColorsRepositoryImpl(
    private val dataSource: MaterialColorsDataSource,
) : MaterialColorsRepository {

    override fun getMaterialColors(): List<ColorGroup> = dataSource.materialColors()
}