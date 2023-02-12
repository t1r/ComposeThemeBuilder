package dev.t1r.themebuilder.repository.colors.material

import dev.t1r.themebuilder.entity.colors.ColorGroup

interface MaterialColorsRepository {
    fun getMaterialColors(): List<ColorGroup>
}