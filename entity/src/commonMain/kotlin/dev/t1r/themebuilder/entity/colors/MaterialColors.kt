package dev.t1r.themebuilder.entity.colors

data class ColorGroup(
    val title: String,
    val items: List<Color>,
)

data class Color(
    val title: String,
    val color: Long,
)

