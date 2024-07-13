rootProject.name = "ComposeThemeBuilder"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
//        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
//        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
//        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
//        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":composeApp")