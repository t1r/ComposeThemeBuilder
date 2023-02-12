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

                implementation(project(Module.entity))
                implementation(project(Module.Feature.root))
                implementation(project(Module.Feature.baselineColor))
                implementation(project(Module.Feature.materialColorsPalette))
                implementation(project(Module.Feature.export))
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
