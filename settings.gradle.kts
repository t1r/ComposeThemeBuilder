pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("multiplatform").version(extra["kotlin.version"] as String)
        kotlin("android").version(extra["kotlin.version"] as String)
        id("com.android.application").version(extra["agp.version"] as String)
        id("com.android.library").version(extra["agp.version"] as String)
        id("org.jetbrains.compose").version(extra["compose.version"] as String)
    }
}

rootProject.name = "ComposeThemeBuilder"

include(
    ":android",
    ":desktop",

    ":common",
    ":ui-compose",
    ":data",

    ":entity",
    ":feature-root",
    ":feature-baseline-colors",
    ":feature-material-colors-pallet",
)
