package dev.t1r.themebuilder.entity.navigation

data class DrawerNavigation(
    val navigateToBaselineColors: () -> Unit,
    val navigateToInputForms: () -> Unit,
)