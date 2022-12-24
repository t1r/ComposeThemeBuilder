package dev.t1r.themebuilder.entity.navigation

data class DrawerNavigationModel(
    val navigateToBaselineColors: () -> Unit,
    val navigateToInputForms: () -> Unit,
)