package dev.t1r.themebuilder.entity.colors

data class ColorGroup(
    val title: String,
    val items: List<ColorModel>,
)

data class ColorModel(
    val title: String,
    val color: Long,
)

