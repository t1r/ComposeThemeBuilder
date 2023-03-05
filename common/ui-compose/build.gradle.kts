plugins {
    id("mpp-setup")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(Deps.Decompose.decompose)
                implementation(Deps.Decompose.extensionsComposeJetbrains)
                implementation(Deps.GosyerAccompanist.pager)

                implementation(project(Module.entity))
                implementation(project(Module.Component.root))
                implementation(project(Module.Component.baselineColor))
                implementation(project(Module.Component.materialColorsPalette))
                implementation(project(Module.Component.export))
            }
        }
        val androidMain by getting {
            dependencies {
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}
